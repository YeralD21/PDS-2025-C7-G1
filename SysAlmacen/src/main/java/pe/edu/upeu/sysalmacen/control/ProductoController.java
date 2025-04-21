package pe.edu.upeu.sysalmacen.control;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.sysalmacen.dtos.ProductoDTO;
import pe.edu.upeu.sysalmacen.excepciones.CustomResponse;
import pe.edu.upeu.sysalmacen.mappers.ProductoMapper;
import pe.edu.upeu.sysalmacen.modelo.Producto;
import pe.edu.upeu.sysalmacen.servicio.IProductoService;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/productos")
public class ProductoController {

    private final IProductoService productoService;
    private final ProductoMapper productoMapper;

    @GetMapping
    public ResponseEntity<List<ProductoDTO>> findAll() {
        List<ProductoDTO> list = productoMapper.toDTOs(productoService.findAll());
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> findById(@PathVariable("id") Long id) {
        Producto obj = productoService.findById(id);
        return ResponseEntity.ok(productoMapper.toDTO(obj));
    }

    @PostMapping
    public ResponseEntity<CustomResponse> save(@Valid @RequestBody ProductoDTO.ProductoCADto dto) {
        ProductoDTO obj = productoService.saveD(dto);
        return ResponseEntity.ok(new CustomResponse(200, LocalDateTime.now(), 
            (obj != null ? "true" : "false"), 
            String.valueOf(obj.getIdProducto())));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoDTO> update(@Valid @RequestBody ProductoDTO.ProductoCADto dto, 
                                            @PathVariable("id") Long id) {
        ProductoDTO obj = productoService.updateD(dto, id);
        return ResponseEntity.ok(obj);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse> delete(@PathVariable("id") Long id) {
        CustomResponse operacion = productoService.delete(id);
        return ResponseEntity.ok(operacion);
    }

    @GetMapping("/pageable")
    public ResponseEntity<Page<ProductoDTO>> listPage(Pageable pageable) {
        Page<ProductoDTO> page = productoService.listaPage(pageable)
            .map(productoMapper::toDTO);
        return ResponseEntity.ok(page);
    }
}