-- timesheetdb.employee definition

CREATE TABLE `employee` (
                            `active` tinyint(1) NOT NULL DEFAULT '1',
                            `employee_id` bigint NOT NULL AUTO_INCREMENT,
                            `email` varchar(50) NOT NULL,
                            `name` varchar(50) NOT NULL,
                            `role` enum('SELLER','BUYER','TECHNICIAN') NOT NULL,
                            PRIMARY KEY (`employee_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- timesheetdb.point_record definition

CREATE TABLE `point_record` (
                                `date` date NOT NULL,
                                `time` time(6) NOT NULL,
                                `employee_id` bigint NOT NULL,
                                `point_record_id` bigint NOT NULL AUTO_INCREMENT,
                                `type` enum('IN','OUT') NOT NULL,
                                PRIMARY KEY (`point_record_id`),
                                KEY `FK8ckitq4h8cwged6nfi35h9ft` (`employee_id`),
                                CONSTRAINT `FK8ckitq4h8cwged6nfi35h9ft` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`employee_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- timesheetdb.`user` definition

CREATE TABLE `user` (
                        `active` tinyint(1) NOT NULL DEFAULT '1',
                        `employee_id` bigint DEFAULT NULL,
                        `password` varchar(2000) NOT NULL,
                        `user_id` varchar(255) NOT NULL,
                        PRIMARY KEY (`user_id`),
                        UNIQUE KEY `UK_r1usl9qoplqsbrhha5e0niqng` (`employee_id`),
                        CONSTRAINT `FK211dk0pe7l3aibwce8yy61ota` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;