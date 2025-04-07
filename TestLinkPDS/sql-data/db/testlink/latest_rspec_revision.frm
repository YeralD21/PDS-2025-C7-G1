TYPE=VIEW
query=select `RSR`.`parent_id` AS `req_spec_id`,`RS`.`testproject_id` AS `testproject_id`,max(`RSR`.`revision`) AS `revision` from (`testlink`.`req_specs_revisions` `RSR` join `testlink`.`req_specs` `RS` on((`RS`.`id` = `RSR`.`parent_id`))) group by `RSR`.`parent_id`,`RS`.`testproject_id`
md5=065506492017fe23ade35164fda822c7
updatable=0
algorithm=0
definer_user=root
definer_host=%
suid=2
with_check_option=0
timestamp=2025-04-07 22:10:16
create-version=1
source=SELECT RSR.parent_id AS req_spec_id, RS.testproject_id AS testproject_id,\nMAX(RSR.revision) AS revision \nFROM /*prefix*/req_specs_revisions RSR \nJOIN /*prefix*/req_specs RS \nON RS.id = RSR.parent_id\nGROUP BY RSR.parent_id,RS.testproject_id
client_cs_name=latin1
connection_cl_name=latin1_swedish_ci
view_body_utf8=select `RSR`.`parent_id` AS `req_spec_id`,`RS`.`testproject_id` AS `testproject_id`,max(`RSR`.`revision`) AS `revision` from (`testlink`.`req_specs_revisions` `RSR` join `testlink`.`req_specs` `RS` on((`RS`.`id` = `RSR`.`parent_id`))) group by `RSR`.`parent_id`,`RS`.`testproject_id`
