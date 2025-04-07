TYPE=VIEW
query=select `lrqvn`.`req_id` AS `req_id`,`lrqvn`.`version` AS `version`,`REQV`.`id` AS `req_version_id` from ((`testlink`.`latest_req_version` `LRQVN` join `testlink`.`nodes_hierarchy` `NHRQV` on((`NHRQV`.`parent_id` = `lrqvn`.`req_id`))) join `testlink`.`req_versions` `REQV` on(((`REQV`.`id` = `NHRQV`.`id`) and (`REQV`.`version` = `lrqvn`.`version`))))
md5=cd1a2590af7aedb77c57ce0e510c57c3
updatable=1
algorithm=0
definer_user=root
definer_host=%
suid=2
with_check_option=0
timestamp=2025-04-07 22:10:16
create-version=1
source=SELECT\n   LRQVN.req_id AS req_id,\n   LRQVN.version AS version,\n   REQV.id AS req_version_id\nFROM latest_req_version LRQVN \nJOIN /*prefix*/nodes_hierarchy NHRQV\nON NHRQV.parent_id = LRQVN.req_id \nJOIN /*prefix*/req_versions REQV \nON REQV.id = NHRQV.id AND REQV.version = LRQVN.version
client_cs_name=latin1
connection_cl_name=latin1_swedish_ci
view_body_utf8=select `lrqvn`.`req_id` AS `req_id`,`lrqvn`.`version` AS `version`,`REQV`.`id` AS `req_version_id` from ((`testlink`.`latest_req_version` `LRQVN` join `testlink`.`nodes_hierarchy` `NHRQV` on((`NHRQV`.`parent_id` = `lrqvn`.`req_id`))) join `testlink`.`req_versions` `REQV` on(((`REQV`.`id` = `NHRQV`.`id`) and (`REQV`.`version` = `lrqvn`.`version`))))
