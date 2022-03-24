INSERT INTO user(user_id, name, email, password, auth_role)
VALUES (1, '이석민', 'user@user.com', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'ROLE_USER');

INSERT INTO user(user_id, name, email, password, auth_role)
VALUES (2, '김수민', 'sumin@user.com', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'ROLE_USER');

INSERT INTO user(user_id, name, email, password, auth_role)
VALUES (3, '강하늘', 'haneul@user.com', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'ROLE_USER');

INSERT INTO user(user_id, name, email, password, auth_role)
VALUES (4, '배고파', 'gopa@user.com', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'ROLE_USER');

INSERT INTO ott_product(ott_product_id, product_name, total_participants_size, created_at, deleted_at, delete_yn)
VALUES (1, 'netflix', 4, '2021-01-01 00:00:00', null, false);

INSERT INTO product_group(product_group_id, ott_product_id, current_participants_size, delete_yn)
VALUES (1, 1, 3, false);