TYPE=VIEW
query=select `NH_TC`.`id` AS `testcase_id`,max(`TCV`.`version`) AS `version` from ((`testlink`.`nodes_hierarchy` `NH_TC` join `testlink`.`nodes_hierarchy` `NH_TCV` on((`NH_TCV`.`parent_id` = `NH_TC`.`id`))) join `testlink`.`tcversions` `TCV` on((`NH_TCV`.`id` = `TCV`.`id`))) group by `testcase_id`
md5=be321b8c9381acba1d67074923ff208a
updatable=0
algorithm=0
definer_user=root
definer_host=%
suid=2
with_check_option=0
timestamp=2025-04-07 22:10:16
create-version=1
source=SELECT NH_TC.id AS testcase_id,max(TCV.version) AS version \nFROM /*prefix*/nodes_hierarchy NH_TC \nJOIN /*prefix*/nodes_hierarchy NH_TCV \nON NH_TCV.parent_id = NH_TC.id\nJOIN /*prefix*/tcversions TCV \nON NH_TCV.id = TCV.id \nGROUP BY testcase_id
client_cs_name=latin1
connection_cl_name=latin1_swedish_ci
view_body_utf8=select `NH_TC`.`id` AS `testcase_id`,max(`TCV`.`version`) AS `version` from ((`testlink`.`nodes_hierarchy` `NH_TC` join `testlink`.`nodes_hierarchy` `NH_TCV` on((`NH_TCV`.`parent_id` = `NH_TC`.`id`))) join `testlink`.`tcversions` `TCV` on((`NH_TCV`.`id` = `TCV`.`id`))) group by `testcase_id`
