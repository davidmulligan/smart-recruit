GRANT ALL ON sr_user.* TO 'smartrecruit'@'%';
GRANT SELECT, INSERT, UPDATE, DELETE ON sr_user.* TO 'smartrecruit'@'%' identified by 'password';

INSERT INTO `smartrecruit`.`ROLE` (`id`, `role`) VALUES ('1', 'USER');
INSERT INTO `smartrecruit`.`ROLE` (`id`, `role`) VALUES ('2', 'ADMIN');

INSERT INTO `smartrecruit`.`USER` (`id`,`activation_code`,`company_name`,`email`,`enabled`,`first_name`,`last_name`,`password`,`profile`,`security_answer`,`security_question`,`total_projects`,`total_rating`,`user_type`,`username`,`membership_id`) VALUES(1, null, null, 'davidmulligan@btopenworld.com', b'1', 'David', 'Mulligan', '$2a$10$hl/Fx1lT9Bk/H4WttkfM.u8HxE2GgrHi60cqej5wWcpz83EYwfDXK', '', null, null, null, null, 'USER', 'david', 1);
INSERT INTO `smartrecruit`.`USER_ROLE` (`users_id`, `roles_id`) VALUES ('1', '1');

INSERT INTO `smartrecruit`.`MEMBERSHIP`(`id`, `created_by`, `created_on`, `modified_by`, `modified_on`, `active`, `cost`, `description`, `name`, `user_type`) VALUES(1, 1, now(), 1, now(), b'1', 0, 'Basic', 'Basic', 'USER');
INSERT INTO `smartrecruit`.`MEMBERSHIP`(`id`, `created_by`, `created_on`, `modified_by`, `modified_on`, `active`, `cost`, `description`, `name`, `user_type`) VALUES(2, 1, now(), 1, now(), b'1', 0, 'Basic', 'Basic', 'CLIENT');

INSERT INTO `smartrecruit`.`SKILL` (`id`, `created_by`, `created_on`, `modified_by`, `modified_on`, `active`, `description`, `name`) VALUES ('1', 1, now(), 1, now(), b'1', 'Java', 'Java');
INSERT INTO `smartrecruit`.`SKILL` (`id`, `created_by`, `created_on`, `modified_by`, `modified_on`, `active`, `description`, `name`) VALUES ('2', 1, now(), 1, now(), b'1', 'HTML', 'HTML');
INSERT INTO `smartrecruit`.`SKILL` (`id`, `created_by`, `created_on`, `modified_by`, `modified_on`, `active`, `description`, `name`) VALUES ('3', 1, now(), 1, now(), b'1', 'Microservices', 'Microservices');
INSERT INTO `smartrecruit`.`SKILL` (`id`, `created_by`, `created_on`, `modified_by`, `modified_on`, `active`, `description`, `name`) VALUES ('4', 1, now(), 1, now(), b'1', 'Docker', 'Docker');
INSERT INTO `smartrecruit`.`SKILL` (`id`, `created_by`, `created_on`, `modified_by`, `modified_on`, `active`, `description`, `name`) VALUES ('5', 1, now(), 1, now(), b'1', 'MySQL', 'MySQL');

INSERT INTO `smartrecruit`.`CATEGORY` (`id`, `created_by`, `created_on`, `modified_by`, `modified_on`, `active`, `description`, `name`) VALUES ('1', 1, now(), 1, now(), b'1', 'Category 1', 'Category 1');
INSERT INTO `smartrecruit`.`CATEGORY` (`id`, `created_by`, `created_on`, `modified_by`, `modified_on`, `active`, `description`, `name`) VALUES ('2', 1, now(), 1, now(), b'1', 'Category 2', 'Category 2');
INSERT INTO `smartrecruit`.`CATEGORY` (`id`, `created_by`, `created_on`, `modified_by`, `modified_on`, `active`, `description`, `name`) VALUES ('3', 1, now(), 1, now(), b'1', 'Category 3', 'Category 3');
INSERT INTO `smartrecruit`.`CATEGORY` (`id`, `created_by`, `created_on`, `modified_by`, `modified_on`, `active`, `description`, `name`) VALUES ('4', 1, now(), 1, now(), b'1', 'Category 4', 'Category 4');
INSERT INTO `smartrecruit`.`CATEGORY` (`id`, `created_by`, `created_on`, `modified_by`, `modified_on`, `active`, `description`, `name`) VALUES ('5', 1, now(), 1, now(), b'1', 'Category 5', 'Category 5');

INSERT INTO `smartrecruit`.`FEEDBACK` (`id`, `average_rating`, `created_by`, `created_on`, `modified_by`, `modified_on`, `review`, `user_id`) VALUES ('1', '5', 1, now(), 1, now(), 'Review 1', '1');
INSERT INTO `smartrecruit`.`FEEDBACK` (`id`, `average_rating`, `created_by`, `created_on`, `modified_by`, `modified_on`, `review`, `user_id`) VALUES ('2', '5', 1, now(), 1, now(), 'Review 2', '1');
INSERT INTO `smartrecruit`.`FEEDBACK` (`id`, `average_rating`, `created_by`, `created_on`, `modified_by`, `modified_on`, `review`, `user_id`) VALUES ('3', '5', 1, now(), 1, now(), 'Review 3', '1');
INSERT INTO `smartrecruit`.`FEEDBACK` (`id`, `average_rating`, `created_by`, `created_on`, `modified_by`, `modified_on`, `review`, `user_id`) VALUES ('4', '5', 1, now(), 1, now(), 'Review 4', '1');
INSERT INTO `smartrecruit`.`FEEDBACK` (`id`, `average_rating`, `created_by`, `created_on`, `modified_by`, `modified_on`, `review`, `user_id`) VALUES ('5', '5', 1, now(), 1, now(), 'Review 5', '1');

INSERT INTO `smartrecruit`.`PROJECT` (`id`, `created_by`, `created_on`, `modified_by`, `modified_on`, `active`, `description`, `title`, `category_id`) VALUES ('1', 1, now(), 1, now(), b'1', 'Tesing', 'Test Job', '1');

INSERT INTO `smartrecruit`.`PROJECT_SKILLS` (`skills_id`, `project_id`) VALUES ('1', '1');
INSERT INTO `smartrecruit`.`PROJECT_SKILLS` (`skills_id`, `project_id`) VALUES ('2', '1');
INSERT INTO `smartrecruit`.`PROJECT_SKILLS` (`skills_id`, `project_id`) VALUES ('3', '1');
INSERT INTO `smartrecruit`.`PROJECT_SKILLS` (`skills_id`, `project_id`) VALUES ('4', '1');
INSERT INTO `smartrecruit`.`PROJECT_SKILLS` (`skills_id`, `project_id`) VALUES ('5', '1');

