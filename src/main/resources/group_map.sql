-- 建表语句
DROP TABLE group_map;
CREATE TABLE group_map (
	group_id NUMBER NOT NULL,
	accounttype_id NUMBER NOT NULL
)
TABLESPACE BILL00 PCTFREE 10 INITRANS 1 MAXTRANS 255
STORAGE(INITIAL 64K MINEXTENTS 1 MAXEXTENTS UNLIMITED);
COMMENT ON TABLE group_map is '组映射表';

-- 修改组映射信息
TRUNCATE TABLE group_map;
insert into GROUP_MAP (group_id, accounttype_id) values (10, 61);
insert into GROUP_MAP (group_id, accounttype_id) values (11, 62);
insert into GROUP_MAP (group_id, accounttype_id) values (12, 81);
COMMIT;
