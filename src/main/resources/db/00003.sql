INSERT INTO permission VALUES ('article_create', NULL);
INSERT INTO permission VALUES ('article_update', NULL);
INSERT INTO permission VALUES ('article_update_own', NULL);
INSERT INTO permission VALUES ('article_delete', NULL);
INSERT INTO permission VALUES ('article_comment_create', NULL);

INSERT INTO role VALUES ('user', NULL);
INSERT INTO role VALUES ('admin', NULL);
INSERT INTO role VALUES ('blogger', NULL);

INSERT INTO roles_permissions (role_id, permission_id) VALUES ('user', 'article_comment_create');

INSERT INTO roles_permissions (role_id, permission_id) VALUES ('admin', 'article_create');
INSERT INTO roles_permissions (role_id, permission_id) VALUES ('admin', 'article_update');
INSERT INTO roles_permissions (role_id, permission_id) VALUES ('admin', 'article_delete');

INSERT INTO roles_permissions (role_id, permission_id) VALUES ('blogger', 'article_create');
INSERT INTO roles_permissions (role_id, permission_id) VALUES ('blogger', 'article_update_own');
