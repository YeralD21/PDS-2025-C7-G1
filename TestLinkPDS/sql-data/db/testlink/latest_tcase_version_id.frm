TYPE=VIEW
query=select `ltcvn`.`testcase_id` AS `testcase_id`,`ltcvn`.`version` AS `version`,`TCV`.`id` AS `tcversion_id` from ((`testlink`.`latest_tcase_version_number` `LTCVN` join `testlink`.`nodes_hierarchy` `NHTCV` on((`NHTCV`.`parent_id` = `ltcvn`.`testcase_id`))) join `testlink`.`tcversions` `TCV` on(((`TCV`.`id` = `NHTCV`.`id`) and (`TCV`.`version` = `ltcvn`.`version`))))
md5=4c827770ddd6a291df9949cb14fb6650
updatable=1
algorithm=0
definer_user=root
definer_host=%
suid=2
with_check_option=0
timestamp=2025-04-07 22:10:16
create-version=1
source=SELECT\n   LTCVN.testcase_id AS testcase_id,\n   LTCVN.version AS version,\n   TCV.id AS tcversion_id\nFROM /*prefix*/latest_tcase_version_number LTCVN \njoin /*prefix*/nodes_hierarchy NHTCV \non NHTCV.parent_id = LTCVN.testcase_id \njoin /*prefix*/tcversions TCV \non TCV.id = NHTCV.id \nand TCV.version = LTCVN.version
client_cs_name=latin1
connection_cl_name=latin1_swedish_ci
view_body_utf8=select `ltcvn`.`testcase_id` AS `testcase_id`,`ltcvn`.`version` AS `version`,`TCV`.`id` AS `tcversion_id` from ((`testlink`.`latest_tcase_version_number` `LTCVN` join `testlink`.`nodes_hierarchy` `NHTCV` on((`NHTCV`.`parent_id` = `ltcvn`.`testcase_id`))) join `testlink`.`tcversions` `TCV` on(((`TCV`.`id` = `NHTCV`.`id`) and (`TCV`.`version` = `ltcvn`.`version`))))
