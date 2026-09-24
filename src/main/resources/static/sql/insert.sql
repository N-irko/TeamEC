SET foreign_key_checks=1;

USE teamdb;

INSERT INTO mst_user
(user_name, password, family_name, first_name, family_name_kana, first_name_kana, gender)
VALUES ('taro@gmail.com', '111111', '山田', '太郎', 'やまだ', 'たろう', 0);

INSERT INTO mst_category (category_name,category_description) VALUES
('下地', 'お肌のベースカラーを整える商品です'),
('アイシャドウ', '目元を美しく彩りお顔を華やかに仕上げる商品です'),
('リップ',' 口元に潤いと彩りをもたらします');

INSERT INTO mst_product(product_name,product_name_kana,product_description,category_id,price,image_full_path,release_date,release_company)VALUES 
('化粧下地：グリーン','けしょうしたじ：ぐりーん','ニキビやお肌の赤み徹底カバー',1,3550,'/img/base_green.png','2026/9/14','自社'),
('化粧下地：ピンク','けしょうしたじ：ぴんく','血色をもたらし、やわらかく温かみのあるお肌を演出',1,3550,'/img/base_pink.png','2026/9/14','自社'),
('化粧下地：パープル','けしょうしたじ：ぱーぷる','透き通るような透明感のあるお肌を演出',1,3550,'/img/base_purple.png','2026/9/14','自社'),
('アイシャドウ：ブラウン','あいしゃどう：ぶらうん','重ね方次第で柔らかくも、モードにも演出',2,3500,'/img/eye_brown.png','2026/9/14','自社'),
('アイシャドウ：ピンク','あいしゃどう：ぴんく','可愛らしい目元を演出',2,3500,'/img/eye_pink.png','2026/9/14','自社'),
('リップ：オレンジ','りっぷ：おれんじ','温かみのあるコーラルオレンジ',3,3200,'/img/lip_orange.png','2026/9/14','自社'),
('リップ：レッド','りっぷ：れっど','自然な血色感を演出',3,3200,'/img/lip_red.png','2026/9/14','自社');
