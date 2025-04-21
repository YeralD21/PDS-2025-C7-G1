package pe.edu.upeu.sysalmacen.control;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pe.edu.upeu.sysalmacen.dtos.report.ProdMasVendidosDTO;
import pe.edu.upeu.sysalmacen.exceptions.FileProcessingException;
import pe.edu.upeu.sysalmacen.exceptions.ReportGenerationException;
import pe.edu.upeu.sysalmacen.modelo.MediaFile;
import pe.edu.upeu.sysalmacen.servicio.IMediaFileService;
import pe.edu.upeu.sysalmacen.servicio.IProductoService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/reporte")
public class ReportController {
    private final IProductoService productoService;
    private final IMediaFileService mfService;
    private final Cloudinary cloudinary;

    @GetMapping("/pmvendidos")
    public List<ProdMasVendidosDTO> getProductosMasVendidos() {
        return productoService.obtenerProductosMasVendidos();
    }

    @GetMapping(value = "/generateReport", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> generateReport() throws ReportGenerationException {
        try {
            byte[] data = productoService.generateReport();
            return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"reporte.pdf\"")
                .body(data);
        } catch (Exception e) {
            throw new ReportGenerationException("Error al generar el reporte", e);
        }
    }

    @GetMapping(value = "/readFile/{idFile}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> readFile(@PathVariable("idFile") Long idFile) throws FileProcessingException {
        try {
            byte[] data = mfService.findById(idFile).getContent();
            return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"archivo_" + idFile + "\"")
                .body(data);
        } catch (Exception e) {
            throw new FileProcessingException("Error al leer el archivo con ID: " + idFile, e);
        }
    }

    @PostMapping(value = "/saveFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> saveFile(@RequestParam("file") MultipartFile multipartFile) throws FileProcessingException {
        try {
            MediaFile mf = new MediaFile();
            mf.setContent(multipartFile.getBytes());
            mf.setFileName(multipartFile.getOriginalFilename());
            mf.setFileType(multipartFile.getContentType());
            mfService.save(mf);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new FileProcessingException("Error al guardar el archivo", e);
        }
    }

    @PostMapping(value = "/saveFileCloud", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> saveFileCloud(@RequestParam("file") MultipartFile multipartFile) throws FileProcessingException {
        Path tempFile = null;
        try {
            tempFile = Files.createTempFile("cloudinary_", "_upload");
            multipartFile.transferTo(tempFile);
            
            cloudinary.uploader().upload(tempFile.toFile(), ObjectUtils.asMap("resource_type", "auto"));
            
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new FileProcessingException("Error al subir el archivo a Cloudinary", e);
        } finally {
            if (tempFile != null) {
                try {
                    Files.deleteIfExists(tempFile);
                } catch (IOException e) {
                    // Logear el error si no se puede eliminar el archivo temporal
                }
            }
        }
    }
}