GRANT ALL ON sr_user.* TO 'smartrecruit'@'%';
GRANT SELECT, INSERT, UPDATE, DELETE ON sr_user.* TO 'smartrecruit'@'%' identified by 'password';

INSERT INTO `smartrecruit`.`ROLE` (`id`, `role`) VALUES ('1', 'USER');
INSERT INTO `smartrecruit`.`ROLE` (`id`, `role`) VALUES ('2', 'ADMIN');

INSERT INTO `smartrecruit`.`MEMBERSHIP`(`id`, `created_by`, `created_on`, `modified_by`, `modified_on`, `active`, `cost`, `description`, `name`, `user_type`) VALUES(1, 1, now(), 1, now(), b'1', 0, 'Basic', 'Basic', 'USER');
INSERT INTO `smartrecruit`.`MEMBERSHIP`(`id`, `created_by`, `created_on`, `modified_by`, `modified_on`, `active`, `cost`, `description`, `name`, `user_type`) VALUES(2, 1, now(), 1, now(), b'1', 0, 'Basic', 'Basic', 'CLIENT');

INSERT INTO `smartrecruit`.`USER` (`id`,`activation_code`,`company_name`,`email`,`enabled`,`first_name`,`last_name`,`password`,`profile`,`security_answer`,`security_question`,`membership_id`)
VALUES(1, null, null, 'davidmulligan@btopenworld.com', b'1', 'David', 'Mulligan', '$2a$10$hl/Fx1lT9Bk/H4WttkfM.u8HxE2GgrHi60cqej5wWcpz83EYwfDXK', '', null, null, 1);
INSERT INTO `smartrecruit`.`USER_ROLE` (`user_id`, `roles_id`) VALUES ('1', '1');

INSERT INTO `smartrecruit`.`SKILL` (`id`, `created_by`, `created_on`, `modified_by`, `modified_on`, `active`, `description`, `name`, `principal`) VALUES ('1', 1, now(), 1, now(), b'1', 'Van Drivers', 'Van Drivers', b'1');
INSERT INTO `smartrecruit`.`SKILL` (`id`, `created_by`, `created_on`, `modified_by`, `modified_on`, `active`, `description`, `name`, `principal`) VALUES ('2', 1, now(), 1, now(), b'1', 'Lighting', 'Lighting', b'1');
INSERT INTO `smartrecruit`.`SKILL` (`id`, `created_by`, `created_on`, `modified_by`, `modified_on`, `active`, `description`, `name`, `principal`) VALUES ('3', 1, now(), 1, now(), b'1', 'Set Painting', 'Set Painting', b'1');
INSERT INTO `smartrecruit`.`SKILL` (`id`, `created_by`, `created_on`, `modified_by`, `modified_on`, `active`, `description`, `name`, `principal`) VALUES ('4', 1, now(), 1, now(), b'1', 'AV Installation', 'AV Equipment Installation', b'1');
INSERT INTO `smartrecruit`.`SKILL` (`id`, `created_by`, `created_on`, `modified_by`, `modified_on`, `active`, `description`, `name`, `principal`) VALUES ('5', 1, now(), 1, now(), b'1', 'Set Building', 'Set Building', b'1');
INSERT INTO `smartrecruit`.`SKILL` (`id`, `created_by`, `created_on`, `modified_by`, `modified_on`, `active`, `description`, `name`, `principal`) VALUES ('6', 1, now(), 1, now(), b'1', 'Health & Safety', 'Health & Safety', b'1');
INSERT INTO `smartrecruit`.`SKILL` (`id`, `created_by`, `created_on`, `modified_by`, `modified_on`, `active`, `description`, `name`, `principal`) VALUES ('7', 1, now(), 1, now(), b'1', 'General Rigging', 'General Rigging', b'1');
INSERT INTO `smartrecruit`.`SKILL` (`id`, `created_by`, `created_on`, `modified_by`, `modified_on`, `active`, `description`, `name`, `principal`) VALUES ('8', 1, now(), 1, now(), b'1', 'Event Crew', 'Event Crew', b'1');

INSERT INTO `smartrecruit`.`CATEGORY` (`id`, `created_by`, `created_on`, `modified_by`, `modified_on`, `active`, `description`, `name`) VALUES ('1', 1, now(), 1, now(), b'1', 'Health & Safety', 'Health & Safety');
INSERT INTO `smartrecruit`.`CATEGORY` (`id`, `created_by`, `created_on`, `modified_by`, `modified_on`, `active`, `description`, `name`) VALUES ('2', 1, now(), 1, now(), b'1', 'Set Builders', 'Set Builders');
INSERT INTO `smartrecruit`.`CATEGORY` (`id`, `created_by`, `created_on`, `modified_by`, `modified_on`, `active`, `description`, `name`) VALUES ('3', 1, now(), 1, now(), b'1', 'Stewards', 'Stewards');
INSERT INTO `smartrecruit`.`CATEGORY` (`id`, `created_by`, `created_on`, `modified_by`, `modified_on`, `active`, `description`, `name`) VALUES ('4', 1, now(), 1, now(), b'1', 'General Crew', 'General Crew');
INSERT INTO `smartrecruit`.`CATEGORY` (`id`, `created_by`, `created_on`, `modified_by`, `modified_on`, `active`, `description`, `name`) VALUES ('5', 1, now(), 1, now(), b'1', 'Drivers', 'Drivers');
INSERT INTO `smartrecruit`.`CATEGORY` (`id`, `created_by`, `created_on`, `modified_by`, `modified_on`, `active`, `description`, `name`) VALUES ('6', 1, now(), 1, now(), b'1', 'Riggers', 'Riggers');
INSERT INTO `smartrecruit`.`CATEGORY` (`id`, `created_by`, `created_on`, `modified_by`, `modified_on`, `active`, `description`, `name`) VALUES ('7', 1, now(), 1, now(), b'1', 'Cleaning Crew', 'Cleaning Crew');
INSERT INTO `smartrecruit`.`CATEGORY` (`id`, `created_by`, `created_on`, `modified_by`, `modified_on`, `active`, `description`, `name`) VALUES ('8', 1, now(), 1, now(), b'1', 'Set Painters' , 'Set Painters');
INSERT INTO `smartrecruit`.`CATEGORY` (`id`, `created_by`, `created_on`, `modified_by`, `modified_on`, `active`, `description`, `name`) VALUES ('9', 1, now(), 1, now(), b'1', 'AV Technicians' , 'AV Technicians');
INSERT INTO `smartrecruit`.`CATEGORY` (`id`, `created_by`, `created_on`, `modified_by`, `modified_on`, `active`, `description`, `name`) VALUES ('10', 1, now(), 1, now(), b'1', 'Electricians', 'Electricians');
INSERT INTO `smartrecruit`.`CATEGORY` (`id`, `created_by`, `created_on`, `modified_by`, `modified_on`, `active`, `description`, `name`) VALUES ('11', 1, now(), 1, now(), b'1', 'Stage Hand', 'Stage Hand');
INSERT INTO `smartrecruit`.`CATEGORY` (`id`, `created_by`, `created_on`, `modified_by`, `modified_on`, `active`, `description`, `name`) VALUES ('12', 1, now(), 1, now(), b'1', 'Runners', 'Runners');
INSERT INTO `smartrecruit`.`CATEGORY` (`id`, `created_by`, `created_on`, `modified_by`, `modified_on`, `active`, `description`, `name`) VALUES ('13', 1, now(), 1, now(), b'1', 'Lighting Technicians', 'Lighting Technicians');
INSERT INTO `smartrecruit`.`CATEGORY` (`id`, `created_by`, `created_on`, `modified_by`, `modified_on`, `active`, `description`, `name`) VALUES ('14', 1, now(), 1, now(), b'1', 'Sound Engineers', 'Sound Engineers');


INSERT INTO `smartrecruit`.`JOB`(`id`,`created_by`,`created_on`,`modified_by`,`modified_on`,`description`,`status`,`title`,`category_id`) VALUES (1,1,now(),1,now(),'Job 1','PENDING','Job 1',1);

INSERT INTO `smartrecruit`.`JOB_SKILLS` (`skills_id`, `job_id`) VALUES ('1', '1');
INSERT INTO `smartrecruit`.`JOB_SKILLS` (`skills_id`, `job_id`) VALUES ('2', '1');
INSERT INTO `smartrecruit`.`JOB_SKILLS` (`skills_id`, `job_id`) VALUES ('3', '1');
INSERT INTO `smartrecruit`.`JOB_SKILLS` (`skills_id`, `job_id`) VALUES ('4', '1');
INSERT INTO `smartrecruit`.`JOB_SKILLS` (`skills_id`, `job_id`) VALUES ('5', '1');

INSERT INTO `smartrecruit`.`FEEDBACK` (`id`, `created_by`, `created_on`, `modified_by`, `modified_on`, `average_score`, `author_id`, `job_id`, `user_id`) VALUES ('1', 1, now(), 1, now(), 5, 1, 1, 1);