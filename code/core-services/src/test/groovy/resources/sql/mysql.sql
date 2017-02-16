GRANT ALL ON sr_user.* TO 'smartrecruit'@'%';
GRANT SELECT, INSERT, UPDATE, DELETE ON sr_user.* TO 'smartrecruit'@'%' identified by 'password';

CREATE SCHEMA `smartrecruit`;

INSERT INTO `smartrecruit`.`ROLE` (`id`, `name`) VALUES ('1', 'CLIENT');
INSERT INTO `smartrecruit`.`ROLE` (`id`, `name`) VALUES ('2', 'FREELANCER');
INSERT INTO `smartrecruit`.`ROLE` (`id`, `name`) VALUES ('3', 'ADMIN');

INSERT INTO `smartrecruit`.`USER` (`id`,`created_by_id`,`created_on`,`modified_by_id`,`modified_on`,`activation_code`,`company_name`,`email`,`enabled`,`first_name`,`last_name`,`password`,`profile`,`security_answer`,`security_question`,`membership_id`)
VALUES(1, 1, now(), 1, now(), null, 'Eureka Technology Limited', 'davidmulligan@btopenworld.com', b'1', 'David', 'Mulligan', '$2a$10$hl/Fx1lT9Bk/H4WttkfM.u8HxE2GgrHi60cqej5wWcpz83EYwfDXK', 'Software Developer & Entrepreneur', null, null, null);

INSERT INTO `smartrecruit`.`MEMBERSHIP`(`id`,`created_by_id`,`created_on`,`modified_by_id`,`modified_on`,`active`,`cost`,`description`,`job_bid_limit`,`job_post_limit`,`name`,`user_type`)
VALUES
(1, 1, now(), 1, now(), b'1', 0.00, 'Basic', 5, 5, 'Basic', 'CLIENT'),
(2, 1, now(), 1, now(), b'1', 100.00, 'Individual', 5, 5, 'Individual', 'CLIENT'),
(3, 1, now(), 1, now(), b'1', 100.00, 'Small Business', 5, 5, 'Small Business', 'CLIENT'),
(4, 1, now(), 1, now(), b'1', 0.00, 'Free', 5, 5, 'Free', 'FREELANCER'),
(5, 1, now(), 1, now(), b'1', 100.00, 'Professional', 5, 5, 'Professional', 'FREELANCER');

UPDATE USER SET membership_id = 1;

INSERT INTO `smartrecruit`.`USER_ROLE` (`user_id`, `roles_id`) VALUES (1, 1);

INSERT INTO `smartrecruit`.`SKILL` (`id`, `created_by_id`, `created_on`, `modified_by_id`, `modified_on`, `active`, `description`, `name`, `principal`)
VALUES
('1', 1, now(), 1, now(), b'1', 'Van Drivers', 'Van Drivers', b'1'),
('2', 1, now(), 1, now(), b'1', 'Lighting', 'Lighting', b'1'),
('3', 1, now(), 1, now(), b'1', 'Set Painting', 'Set Painting', b'1'),
('4', 1, now(), 1, now(), b'1', 'AV Installation', 'AV Equipment Installation', b'1'),
('5', 1, now(), 1, now(), b'1', 'Set Building', 'Set Building', b'1'),
('6', 1, now(), 1, now(), b'1', 'Health & Safety', 'Health & Safety', b'1'),
('7', 1, now(), 1, now(), b'1', 'General Rigging', 'General Rigging', b'1'),
('8', 1, now(), 1, now(), b'1', 'Event Crew', 'Event Crew', b'1');

INSERT INTO `smartrecruit`.`USER_SKILLS`(`user_id`,`skills_id`)
VALUES
(1,3),
(1,5),
(1,4),
(1,7),
(1,6);

INSERT INTO `smartrecruit`.`CATEGORY` (`id`, `created_by_id`, `created_on`, `modified_by_id`, `modified_on`, `active`, `description`, `name`, `principal`)
VALUES
('1', 1, now(), 1, now(), b'1', 'Health & Safety', 'Health & Safety', b'1'),
('2', 1, now(), 1, now(), b'1', 'Set Builders', 'Set Builders', b'1'),
('3', 1, now(), 1, now(), b'1', 'Stewards', 'Stewards', b'1'),
('4', 1, now(), 1, now(), b'1', 'General Crew', 'General Crew', b'1'),
('5', 1, now(), 1, now(), b'1', 'Drivers', 'Drivers', b'1'),
('6', 1, now(), 1, now(), b'1', 'Riggers', 'Riggers', b'1'),
('7', 1, now(), 1, now(), b'1', 'Cleaning Crew', 'Cleaning Crew', b'1'),
('8', 1, now(), 1, now(), b'1', 'Set Painters' , 'Set Painters', b'1'),
('9', 1, now(), 1, now(), b'1', 'AV Technicians' , 'AV Technicians', b'1'),
('10', 1, now(), 1, now(), b'1', 'Electricians', 'Electricians', b'1'),
('11', 1, now(), 1, now(), b'1', 'Stage Hand', 'Stage Hand', b'1'),
('12', 1, now(), 1, now(), b'1', 'Runners', 'Runners', b'1'),
('13', 1, now(), 1, now(), b'1', 'Lighting Technicians', 'Lighting Technicians', b'1'),
('14', 1, now(), 1, now(), b'1', 'Sound Engineers', 'Sound Engineers', b'1');

INSERT INTO `smartrecruit`.`JOB`
(`id`,`created_on`,`modified_on`,`deadline`,`description`,`duration`,`fixed`,`location`,`number_positions`,`remuneration`,`status`,`title`,`created_by_id`,`modified_by_id`,`category_id`)
VALUES
(1,now(),now(),now(),'Example Job',1,b'1','Lonon',1,100,'NEW','Example Job',1,1,1);

INSERT INTO `smartrecruit`.`JOB_SKILLS` (`skills_id`, `job_id`)
VALUES
('1', '1'),
('2', '1'),
('3', '1'),
('4', '1'),
('5', '1');

INSERT INTO `smartrecruit`.`FEEDBACK` (`id`, `created_by_id`, `created_on`, `modified_by_id`, `modified_on`, `rating`, `job_id`, `user_id`)
VALUES ('1', 1, now(), 1, now(), 5, 1, 1);