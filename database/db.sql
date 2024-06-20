-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: onlinecourse_db
-- ------------------------------------------------------
-- Server version	8.0.36

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `answers`
--

DROP TABLE IF EXISTS `answers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `answers` (
  `id` int NOT NULL AUTO_INCREMENT,
  `content` varchar(200) NOT NULL,
  `is_correct` bit(1) NOT NULL,
  `quiz_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5qlfxfuy6hvtlmirhd8drt3wi` (`quiz_id`),
  CONSTRAINT `FK5qlfxfuy6hvtlmirhd8drt3wi` FOREIGN KEY (`quiz_id`) REFERENCES `quizzes` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `answers`
--

LOCK TABLES `answers` WRITE;
/*!40000 ALTER TABLE `answers` DISABLE KEYS */;
INSERT INTO `answers` VALUES (1,'Ctrl + Shift + tab',_binary '\0',1),(2,'Ctrl + Shift + w',_binary '',1),(3,'Ctrl + Alt + r',_binary '\0',1),(4,'Windows Subsystem for Linner',_binary '\0',2),(5,'Windows Subsystem of Linux',_binary '\0',2),(6,'Windows Subscribe for Linux',_binary '\0',2),(7,'Windows Subsystem for Linux',_binary '',2),(16,'hello world',_binary '',5),(17,'<input type= \"text\">',_binary '\0',6),(18,'<input type= \"submit\">',_binary '',6),(19,'<input type= \"password\">',_binary '\0',6),(20,'<input type= \"reset\">',_binary '\0',6),(21,'inline',_binary '',7),(22,'block',_binary '',7),(23,'hidden',_binary '\0',7),(24,'flex',_binary '',7),(25,'none',_binary '',7),(26,'inline-block',_binary '',7),(27,'href',_binary '',8),(28,'đáp án 1',_binary '\0',4),(29,'đáp án 1',_binary '\0',4),(30,'đáp án 1',_binary '\0',4),(31,'đáp án 1',_binary '',4),(44,'ONE_CHOICE',_binary '',3),(45,'ONE_CHOICE',_binary '\0',3),(46,'ONE_CHOICE',_binary '\0',3),(47,'123123',_binary '',11),(48,'123123123',_binary '',11),(49,'123123',_binary '',11);
/*!40000 ALTER TABLE `answers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `blog`
--

DROP TABLE IF EXISTS `blog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `blog` (
  `id` int NOT NULL AUTO_INCREMENT,
  `content` text NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `description` text NOT NULL,
  `slug` varchar(255) NOT NULL,
  `thumbnail` varchar(255) NOT NULL,
  `title` varchar(70) NOT NULL,
  `view` int NOT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_rse7kjvydwev63jjbsm0dw4ey` (`slug`),
  UNIQUE KEY `UK_rnrou1m94mucgt39epcw8ov59` (`title`),
  KEY `FKkr2fy24puc3x3sdnla4r1iok1` (`user_id`),
  CONSTRAINT `FKkr2fy24puc3x3sdnla4r1iok1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blog`
--

LOCK TABLES `blog` WRITE;
/*!40000 ALTER TABLE `blog` DISABLE KEYS */;
INSERT INTO `blog` VALUES (2,'<h2><span class=\"ql-size-large\">I. Giới thiệu sơ lược</span></h2><p><span class=\"ql-size-large\">Hello anh em , thì như&nbsp;</span><a href=\"https://fullstack.edu.vn/blog/cach-chinh-theme-oh-my-posh-cho-powershell.html\" rel=\"noopener noreferrer\" target=\"_blank\" class=\"ql-size-large\">blog trước</a><span class=\"ql-size-large\">&nbsp;mình có nói rằng mình ko có dùng Ubuntu, nhưng sao lại có blog này ??</span></p><p><span class=\"ql-size-large\">À thì mình mới cài lại Win 10, vì máy mình cũng yếu ?, mà ko có tiền mua nên mình đã cài lùi về Win10 xài cho nó sướng nha. Chứ đừng có nói mình bị thành người \"tối cổ \" nha ?.</span></p><p><span class=\"ql-size-large\">Lại nói về trước đó nữa, mình đã tu luyện thành pháp sư WSL nhưng vì Win11 mình cài nó cứ lag và cấu hình ko hợp nên mình có thành tài cũng đến Tết Công Gô mới cài nổi. May thay, nhờ có anh F8 chỉ cách cài trên Win10 và phần vì mình cũng đang xài con Win10 nên mình cài thử, ko ngờ lại thành công ngoài sức tưởng tưởng ! Chắc mình truyền lại cái Win10 cho con cháu Dev đời sau luôn quá ?</span></p><p><span class=\"ql-size-large\">Hình ành của con em WSL sau khi config:</span></p><p><br></p><p><span class=\"ql-size-large\"><img src=\"https://files.fullstack.edu.vn/f8-prod/blog_posts/10265/6628df53e25a0.png\"></span></p><p><br></p><p><strong class=\"ql-size-large\">Thôi lòng vòng đủ rồi, hôm nay mình sẽ chỉ các bạn tóm tắt cách setup một em WSL giống mình nhé ?</strong></p><h3><span class=\"ql-size-large\">Nhớ coi xong like nhiều cho mình nếu các bạn cũng làm được nhé ?</span></h3><h2><br></h2><h2><span class=\"ql-size-large\">II Hành trình tu luyện WSL ?</span></h2><h3><span class=\"ql-size-large\">Bước 1: Cài WSL:</span></h3><p><span class=\"ql-size-large\">Hiện nay có khá nhiều bạn đang dùng Powershell nhưng lại lười config lên một đẳng cấp mới như WSL, và người ta luôn có câu:</span></p><blockquote><span class=\"ql-size-large\">Trên bước đường thành công ko có dấu chân của kẻ thông minh, người lười biếng họ ko đi bộ, họ đi bằng ô tô, trực thăng, tên lửa ? (Thánh Wibu)</span></blockquote><p><span class=\"ql-size-large\">Vậy nên mình sẽ giúp người lười biếng thành công nhé!</span></p><p><span class=\"ql-size-large\">Coi video này để nắm rõ các bước nhé!&nbsp;</span><a href=\"https://fullstack.edu.vn/external-url?continue=https%3A%2F%2Fwww.youtube.com%2Fwatch%3Fv%3Dypvjxw5qBK0\" rel=\"noopener noreferrer\" target=\"_blank\" class=\"ql-size-large\">https://www.youtube.com/watch?v=ypvjxw5qBK0</a></p><p><strong class=\"ql-size-large\">Anh em nhớ là hãy cài Ubuntu hay Debian cho nó tương thích code của mình nhoa ?</strong></p><h3><span class=\"ql-size-large\">Bước 2: Cài Zsh:</span></h3><p><span class=\"ql-size-large\">Wait wait! Đợi đã, vội chi, vội là ch*t đấy!? Vì chúng ta chưa update package của ubuntu nên cần update nha:</span></p><pre class=\"ql-syntax\" spellcheck=\"false\">sudo apt update &amp;&amp; sudo apt upgrade\n</pre><p><span class=\"ql-size-large\">Rồi install luôn build essential để phòng trừ thiên tai cho tương lai gần:</span></p><pre class=\"ql-syntax\" spellcheck=\"false\">sudo apt install build-essential\n</pre><p><span class=\"ql-size-large\">Giờ mới tải Zsh nhoa ?:</span></p><pre class=\"ql-syntax\" spellcheck=\"false\">sudo apt install zsh\n</pre><p><span class=\"ql-size-large\">Cài thêm một số thứ như Git nhé:</span></p><pre class=\"ql-syntax\" spellcheck=\"false\">sudo apt install git\n</pre><p><span class=\"ql-size-large\">Các bạn copy từng đoạn một các bạn nhớ nhập mật khẩu các bạn vào nhé! Ko thì toang</span></p><p><span class=\"ql-size-large\">GIờ thì bắt đầu cài đặt Zsh nhé!</span></p><h3><span class=\"ql-size-large\">3. Hành trình tu luyện Zsh</span></h3><p><span class=\"ql-size-large\">Giờ các bạn hãy cài Oh-my-zsh theo curl nhé!</span></p><pre class=\"ql-syntax\" spellcheck=\"false\">sh -c \"$(curl -fsSL https://raw.githubusercontent.com/ohmyzsh/ohmyzsh/master/tools/install.sh)\"\n</pre><p><span class=\"ql-size-large\">Các bạn cũng có thể cài qua wget nữa:</span></p><pre class=\"ql-syntax\" spellcheck=\"false\">sh -c \"$(wget https://raw.githubusercontent.com/ohmyzsh/ohmyzsh/master/tools/install.sh -O -)\"\n</pre><p><span class=\"ql-size-large\">Cài xong nó hỏi gì các bạn cứ yes hết cho mình nha.</span></p><h3><span class=\"ql-size-large\">Lưu ý quan trọng: Các bạn hãy cài 1 cái Nerd Font và một cái theme cho Window Terminal nhé nếu ko muốn thất bại trong bước tiếp theo</span></h3><p><span class=\"ql-size-large\">Giờ hãy cài Powerlevel10k nhé:</span></p><pre class=\"ql-syntax\" spellcheck=\"false\">git clone --depth=1 https://github.com/romkatv/powerlevel10k.git ${ZSH_CUSTOM:-$HOME/.oh-my-zsh/custom}/themes/powerlevel10k\n</pre><p><span class=\"ql-size-large\">rồi các bạn nhập cho mình</span></p><pre class=\"ql-syntax\" spellcheck=\"false\">vi ~/.zshrc\n</pre><p><span class=\"ql-size-large\">Hãy sửa cài phần theme nhé!</span></p><p><span class=\"ql-size-large\"><img src=\"https://files.fullstack.edu.vn/f8-prod/blog_posts/10265/6628e592e0ab8.png\"></span></p><p><br></p><p><span class=\"ql-size-large\">Sau khi xong rồi các bạn load màn hình Ubuntu mới là sẽ có sự khác biệt:</span></p><p><br></p><p><span class=\"ql-size-large\"><img src=\"https://files.fullstack.edu.vn/f8-prod/blog_posts/10265/6628e5dabe1b6.png\"></span></p><p><br></p><p><span class=\"ql-size-large\">Làm theo yêu cầu của nó là sẽ có kết quả ngon thôi ?!</span></p><h3><span class=\"ql-size-large\">Bước 3: Các config phụ:</span></h3><p><span class=\"ql-size-large\">Các bạn hãy code theo mình nhé!</span></p><ol><li><span class=\"ql-size-large\">Cài zsh-autosuggestions:</span></li></ol><pre class=\"ql-syntax\" spellcheck=\"false\">git clone https://github.com/zsh-users/zsh-autosuggestions ${ZSH_CUSTOM:-~/.oh-my-zsh/custom}/plugins/zsh-autosuggestions\n</pre><p><span class=\"ql-size-large\">Rồi tới zsh-syntax-highlighting</span></p><pre class=\"ql-syntax\" spellcheck=\"false\">git clone https://github.com/zsh-users/zsh-syntax-highlighting.git ${ZSH_CUSTOM:-~/.oh-my-zsh/custom}/plugins/zsh-syntax-highlighting\n</pre><p><span class=\"ql-size-large\">Xong rồi thì các bạn chỉnh phần plugins=(...) trong ~/.zshrc cho mình nhé:</span></p><p><br></p><p><span class=\"ql-size-large\"><img src=\"https://files.fullstack.edu.vn/f8-prod/blog_posts/10265/6628e752064eb.png\"></span></p><h2><br></h2><h2><span class=\"ql-size-large\">III. Kết luận</span></h2><p><span class=\"ql-size-large\">Vậy là mình đã setup xong 1 em WSL rồi, giờ có thể code thỏa thích luôn ?, mình chức các bạn thành công trong việc setup nhé!</span></p>','2024-05-16 20:13:09.448000','Hello anh em , thì như blog trước mình có nói rằng mình ko có dùng Ubuntu, nhưng sao lại có...','config-zsh-bang-ohmyzsh-va-p10k-tren-wsl-cuc-ngau','https://res.cloudinary.com/dqnoopa0x/image/upload/v1715865191/mqrxgvxfx2cmffh8lnoj.png','Config Zsh bằng Oh-my-zsh và P10k trên WSL cực ngầu ✨',7,3),(3,'<p><span class=\"ql-size-large\">Bài viết này đơn giản là nơi để mình lưu lại những kinh nghiệm mình đã làm việc với HTML/CSS cũng như chia sẻ phần nào cho bạn nào chưa biết. Mình cùng bắt đầu bài viết thôi, yooolo?</span></p><p><span class=\"ql-size-large\">Nội dung bài viết:</span></p><ol><li><span class=\"ql-size-large\">Bố trí CSS cho gọn, dễ nhìn</span></li><li><span class=\"ql-size-large\">Vì sao khi sử dụng media queries thì nên sd đơn vị em hơn là đơn vị rem</span></li><li><span class=\"ql-size-large\">Reset CSS phong cách \"hại điện\"</span></li></ol><p><br></p><h2><span class=\"ql-size-large\">1. Bố trí CSS cho gọn, dễ nhìn</span></h2><p><span class=\"ql-size-large\">Khi làm việc với CSS, ắt hẳn ae sẽ theo nhiều trường phái khác nhau khi đặt code: đặt random, đặt theo alphabet, nhóm theo group</span></p><p><span class=\"ql-size-large\">Thì theo như khảo sát từ&nbsp;css-tricks&nbsp;(một trang web nổi tiếng về các bài viết chất lượng về CSS, các bạn có thể tìm hiểu thêm ở link này&nbsp;</span><a href=\"https://fullstack.edu.vn/external-url?continue=https%3A%2F%2Fcss-tricks.com%2F\" rel=\"noopener noreferrer\" target=\"_blank\" class=\"ql-size-large\">CSS-Trick</a><span class=\"ql-size-large\">, đa số ae Developer chúng ta chọn&nbsp;</span><strong class=\"ql-size-large\">Nhóm theo group</strong><span class=\"ql-size-large\">&nbsp;(Chiếm tới&nbsp;43%)</span></p><p><span class=\"ql-size-large\"><img src=\"https://files.fullstack.edu.vn/f8-prod/blog_posts/7923/64a24256e58fb.png\"></span></p><p><br></p><p><span class=\"ql-size-large\">Chúng ta cùng nhau nhìn xem sự khác biệt khi bố trí 1 cách&nbsp;</span><strong class=\"ql-size-large\">random</strong><span class=\"ql-size-large\">&nbsp;(mình thấy nhiều ae sẽ viết theo cách này, kể cả mình trước đây) Đây là cách&nbsp;</span><strong class=\"ql-size-large\">bố trí CSS theo random</strong></p><p><br></p><pre class=\"ql-syntax\" spellcheck=\"false\">.selector {\n  top: 0;\n  right: 0;\n  display: inline-block;\n  overflow: hidden;\n  box-sizing: border-box;\n  font-family: sans-serif;\n  font-size: 16px;\n  line-height: 1.4;\n  position: absolute;\n  z-index: 10;\n  background: #000;\n  color: #fff\n  width: 100px;\n  height: 100px;\n  padding: 10px;\n  border: 10px solid #333;\n  margin: 10px;\n  cursor: pointer;\n  text-align: right;\n}\n</pre><p><br></p><p><span class=\"ql-size-large\">Có bạn nào nhìn nhanh qua đoạn code trên mà trả lời được các câu hỏi:&nbsp;</span><em class=\"ql-size-large\">sử dụng font-family gì</em><span class=\"ql-size-large\">,&nbsp;</span><em class=\"ql-size-large\">đang sử dụng position absolute hay relative</em><span class=\"ql-size-large\">,&nbsp;</span><em class=\"ql-size-large\">font-size là bao nhiêu ta</em></p><p><span class=\"ql-size-large\">Nhiều bạn sẽ bảo trong dự án thực tế làm gì có đoạn code CSS nào dài dữ dằn ?, nhưng đây là code mình lấy từ dự án thực tế luôn nha các bạn, nhìn sơ qua chúng ta cũng thấy nó khó quản lí thế nào rồi phải không</span></p><p><span class=\"ql-size-large\">Chúng ta cùng đến với đoạn code sau:</span></p><p><br></p><pre class=\"ql-syntax\" spellcheck=\"false\">.selector {\n  /* Positioning */\n  position: absolute;\n  z-index: 10;\n  top: 0;\n  right: 0;\n\n  /* Display &amp; Box Model */\n  display: inline-block;\n  overflow: hidden;\n  box-sizing: border-box;\n  width: 100px;\n  height: 100px;\n  padding: 10px;\n  border: 10px solid #333;\n  margin: 10px;\n\n  /* Color */\n  background: #000;\n  color: #fff\n  \n  /* Text */\n  font-family: sans-serif;\n  font-size: 16px;\n  line-height: 1.4;\n  text-align: right;\n\n  /* Other */\n  cursor: pointer;\n}\n</pre><p><br></p><p><span class=\"ql-size-large\">Tèn ten, chòi oi, ở trên rối bao nhiêu, ở đây lại dễ hiểu bấy nhiêu. Giỡn thôi, nhưng mà phải công nhận cách tiếp cận này thì mình sẽ dễ dàng tìm kiếm cũng như quản lí hơn phải k nè.</span></p><p><span class=\"ql-size-large\">Tin mình đi, các bạn k phải lo phải ghi nhớ từng group khi code đâu, giống như khi mới học CSS vậy, mưa dầm thấm lâu đúng không nè.</span></p><p><span class=\"ql-size-large\">Nhưng, cũng sẽ có cách để các bạn auto format theo group như trên mà k cần ngồi tự sắp xếp. Các bạn tìm hiểu thư viện&nbsp;stylint-config-rational-order&nbsp;nhé, quá đã phải không nào ?</span></p><p><br></p><h2><span class=\"ql-size-large\">2. Vì sao khi sử dụng media queries thì nên sd đơn vị em hơn là đơn vị rem</span></h2><p><span class=\"ql-size-large\">Nhìn chung, cả 2 thằng&nbsp;rem&nbsp;và em nó cũng tương tự nhau cả thôi</span></p><p><span class=\"ql-size-large\">Nhưng, thằng&nbsp;em&nbsp;nó sẽ có 1 điều tốt hơn mà thằng&nbsp;rem&nbsp;không có</span></p><p><span class=\"ql-size-large\">Vì khi user zoom trang web lên (sử dụng&nbsp;Ctrl&nbsp;+ dấu&nbsp;+&nbsp;→ đơn vị em sẽ cộng thêm phần width được zoom vào, còn px và rem thì không (chỉ có safari mới đúng khi user zoom))</span></p><p><br></p><p><span class=\"ql-size-large\"><img src=\"https://files.fullstack.edu.vn/f8-prod/blog_posts/7923/64a245a429422.png\"></span></p><p><br></p><p><span class=\"ql-size-large\">Tóm lại, khi dùng&nbsp;media queries&nbsp;chúng ta nên sử dụng đơn vị&nbsp;em&nbsp;hơn là&nbsp;rem</span></p><p><span class=\"ql-size-large\">Dưới đây là các demo khi sử dụng&nbsp;em</span></p><p><span class=\"ql-size-large\">Thay vì sử dụng&nbsp;px</span></p><p><br></p><pre class=\"ql-syntax\" spellcheck=\"false\">@media screen and (max-width: 768px) {\n// code...\n}\n</pre><p><br></p><p><span class=\"ql-size-large\">Ta chuyển sang&nbsp;em</span></p><p><br></p><pre class=\"ql-syntax\" spellcheck=\"false\">@media screen and (max-width: 48em) {\n// code...\n}\n</pre><p><br></p><p><span class=\"ql-size-large\">Bạn có thể dùng trang web này để chuyển từ&nbsp;px&nbsp;sang&nbsp;em:&nbsp;</span><a href=\"https://fullstack.edu.vn/external-url?continue=https%3A%2F%2Fnekocalc.com%2Fpx-to-em-converter\" rel=\"noopener noreferrer\" target=\"_blank\" class=\"ql-size-large\">https://nekocalc.com/px-to-em-converter</a></p><h2><span class=\"ql-size-large\">3. Reset CSS phong cách \"hại điện\"</span></h2><p><br></p><p><span class=\"ql-size-large\">Khi làm việc với HTML/CSS, không ai lạ với việc sử dụng Reset CSS ban đầu để trang web mình có thể giống nhau nhất với mọi trình duyệt phải không nè</span></p><p><span class=\"ql-size-large\">Đây là cách mà mình học được từ các blogger nổi tiếng:</span></p><pre class=\"ql-syntax\" spellcheck=\"false\">/*\n  1. Use a more-intuitive box-sizing model.\n*/\n*,\n*::before,\n*::after {\n    box-sizing: border-box;\n    border: none;\n}\n* {\n    font: inherit;\n}\n/*\n  2. Remove default margin\n*/\n* {\n    margin: 0;\n}\n/*\n  3. Allow percentage-based heights in the application\n*/\nhtml {\n    font-size: 62.5%; /* (62.5/100) * 16px = 10px */\n}\nhtml,\nbody {\n    height: 100%;\n}\n/*\n  Typographic tweaks!\n  4. Add accessible line-height\n  5. Improve text rendering\n*/\nbody {\n    line-height: 1.5;\n    -webkit-font-smoothing: antialiased;\n}\n/*\n  6. Improve media defaults\n*/\nimg,\npicture,\nvideo,\ncanvas,\nsvg {\n    display: block;\n    max-width: 100%;\n}\n/*\n  7. Remove built-in form typography styles\n*/\ninput,\nbutton,\ntextarea,\nselect {\n    font: inherit;\n}\n/*\n  8. Avoid text overflows\n*/\np,\nh1,\nh2,\nh3,\nh4,\nh5,\nh6 {\n    overflow-wrap: break-word;\n}\n/*\n  9. Create a root stacking context\n*/\n#root,\n#__next {\n    isolation: isolate;\n}\n/*\n  10. Smooth scrolling only when the user does not have vestibular disorders\n*/\n@media (prefers-reduced-motion: no-preference) {\n    html {\n        scroll-behavior: smooth;\n    }\n}\nmenu:not(article menu),\nol:not(article ol),\nul:not(article ul) {\n    list-style: none;\n}\nmenu,\nol,\nul {\n    padding-left: 0;\n}\n/*\n  11. Only outline when using keyboard\n*/\n:focus:not(:focus-visible) {\n    outline: none;\n}\n/*\n  12. Add pointers for required components\n*/\nlabel,\nbutton,\nselect,\nsummary,\n[type=\"radio\"],\n[type=\"submit\"],\n[type=\"checkbox\"] {\n    cursor: pointer;\n}\n</pre><p><br></p><p><span class=\"ql-size-large\">Nguồn tham khảo:&nbsp;</span><a href=\"https://fullstack.edu.vn/external-url?continue=https%3A%2F%2Fwww.joshwcomeau.com%2Fcss%2Fcustom-css-reset%2F\" rel=\"noopener noreferrer\" target=\"_blank\" class=\"ql-size-large\">https://www.joshwcomeau.com/css/custom-css-reset/</a><span class=\"ql-size-large\">,&nbsp;</span><a href=\"https://fullstack.edu.vn/external-url?continue=https%3A%2F%2Fblog.logrocket.com%2Fwhat-should-modern-css-boilerplate-look-like%2F\" rel=\"noopener noreferrer\" target=\"_blank\" class=\"ql-size-large\">https://blog.logrocket.com/what-should-modern-css-boilerplate-look-like/</a></p><p><span class=\"ql-size-large\">Trên đây là 1 số tips khi làm việc với CSS, bạn nào thấy thú vị thì có thể comment bên dưới để cùng mình thảo luận ?</span></p>','2024-05-16 20:35:20.490000','Bài viết này đơn giản là nơi để mình lưu lại những kinh nghiệm mình đã làm việc với HTML/CSS cũng như chia sẻ phần nào cho bạn...','mot-so-cam-nang-hay-khi-lam-viec-voi-htmlcss','https://res.cloudinary.com/dqnoopa0x/image/upload/v1715866522/jd7sgy4hrs9yr9sltsjq.jpg','Một số \"cẩm nang\" hay khi làm việc với HTML/CSS? ',3,4),(4,'<p><em class=\"ql-size-large\">Chắc hẳn hầu hết các bạn trong giới lập trình đều đã ít nhất một lần nghe đến từ “fullstack”. Tuy nhiên vẫn còn nhiều người hiểu lầm về khái niệm đang hot này. Cùng đọc bài viết để biết fullstack là gì nhé!</em></p><h2><span class=\"ql-size-large\">1. Fullstack là gì? Thế nào là lập trình viên fullstack?</span></h2><p><span class=\"ql-size-large\"><img src=\"https://files.fullstack.edu.vn/f8-staging/blog_posts/4059/63f57db6af548.jpg\"></span><em class=\"ql-size-large\">Fullstack là gì? Thế nào là lập trình viên fullstack?</em></p><h3><span class=\"ql-size-large\">Khái niệm fullstack là gì?</span></h3><p><span class=\"ql-size-large\">Xét trong lĩnh vực công nghệ, nhắc tới fullstack chính là nói đến hàng loạt các công nghệ cần có để có thể hoàn thiện được một dự án. Fullstack developer (lập trình viên fullstack) chính là những người có khả năng làm việc cả trên back-end, front-end lẫn database, hệ thống, bảo mật, server,...</span></p><h3><span class=\"ql-size-large\">Thế nào là lập trình viên Fullstack?</span></h3><p><span class=\"ql-size-large\">Tuy nhiên, nói như vậy không có nghĩa là một lập trình viên fullstack cần phải giỏi trong tất cả các lĩnh vực trên mà họ chỉ là những người hiểu rõ nhất về những công nghệ cần có cho dự án và các công việc mà mình đang làm. Điều đặc biệt là một lập trình viên fullstack hoàn toàn có khả năng update và học hỏi thêm nhiều kiến thức, công nghệ khác khi cần thiết.</span></p><p><span class=\"ql-size-large\">Có thể bạn chưa biết, trước năm 2010, rất ít người sử dụng và biết đến khái niệm fullstack trong giới công nghệ thông tin. Nguồn gốc của việc từ này được sử dụng rộng rãi và ưa chuộng là bởi Facebook đưa ra thông báo rằng họ sẽ chỉ tuyển dụng fullstack developer trong sự kiện mã nguồn mở OSCON ((Open Source Software Conference) năm 2012, chính từ đấy, khái niệm fullstack mới dần dần trở nên thông dụng và ngày một được ưa chuộng nhiều hơn.</span></p><h3><span class=\"ql-size-large\">Công việc của một lập trình viên fullstack là gì?</span></h3><p><span class=\"ql-size-large\"><img src=\"https://files.fullstack.edu.vn/f8-staging/blog_posts/4059/63f57dd4411ec.jpg\"></span><em class=\"ql-size-large\">Công việc của một lập trình viên fullstack là gì?</em></p><p><span class=\"ql-size-large\">Thông thường, một lập trình viên fullstack sẽ làm những công việc như:</span></p><p><span class=\"ql-size-large\">Đưa ra các giải pháp, khuyến nghị để xây dựng cấu trúc cũng như các ứng dụng theo yêu cầu.</span></p><p><span class=\"ql-size-large\">Quản lý các dự án, làm việc với các stakeholder</span></p><p><span class=\"ql-size-large\">Viết code backend với nhiều ngôn ngữ lập trình khác như Java, PHP, Python…</span></p><p><span class=\"ql-size-large\">Sáng tạo và làm việc trên Front-end, sử dụng nhiều html, CSS, JavaScript,..</span></p><p><span class=\"ql-size-large\">Khởi tạo và phát triển cơ sở dữ liệu</span></p><p><span class=\"ql-size-large\">Xây dựng các API</span></p><p><span class=\"ql-size-large\">Kiểm tra quá trình thử nghiệm ứng dụng</span></p><p><span class=\"ql-size-large\">Nâng cấp performance của các ứng dụng</span></p><p><span class=\"ql-size-large\">Tham gia vào quá trình xây dựng các tài liệu kỹ thuật. ….</span></p><h2><span class=\"ql-size-large\">2. Kỹ năng cần có của lập trình viên fullstack là gì?</span></h2><p><span class=\"ql-size-large\">Như vậy, chúng ta có thể hiểu rằng để trở thành một lập trình viên fullstack thì sẽ cần có rất nhiều kỹ năng chuyên môn, các kỹ năng đó cụ thể là gì?<img src=\"https://files.fullstack.edu.vn/f8-staging/blog_posts/4059/63f57dea8d4fa.jpg\"></span><em class=\"ql-size-large\">Kỹ năng cần có của lập trình viên fullstack là gì?</em></p><h3><span class=\"ql-size-large\">Ngôn ngữ lập trình</span></h3><p><span class=\"ql-size-large\">Một số ngôn ngữ lập trình cơ bản như PHP, C#, Python bạn cần phải nắm rõ. Bởi bạn sẽ phải kiểm tra các đối tượng đã được xây dựng từ ngôn ngữ lập trình trong dự án. Ngoài ra, bạn cũng cần học thêm một số ngôn ngữ khác để hỗ trợ cho công việc.</span></p><h3><span class=\"ql-size-large\">Kỹ năng về framework, nền tảng, thư viện</span></h3><p><span class=\"ql-size-large\">Khi bạn xây dựng các dịch vụ đám mây hoặc các ứng dụng real-time data, các nền tảng hay framework là điều chắc chắn bạn phải sử dụng. Chính vì thế, bạn không thể trở thành một Full Stack Developer nếu không có kiến thức về framework.</span></p><h3><span class=\"ql-size-large\">Cơ sở dữ liệu và caching</span></h3><p><span class=\"ql-size-large\">Bạn cần biết ít nhất một trong các hệ thống cơ sở dữ liệu sau:Oracle, MySQL, SQL Server,… Ngoài ra, bạn cũng bị đòi hỏi các kỹ thuật về caching như Redis, varnish, memcached,…</span></p><h3><span class=\"ql-size-large\">Kỹ năng thiết kế</span></h3><p><span class=\"ql-size-large\">Đây là kỹ năng nhà tuyển dụng không đòi hỏi quá nhiều ở bạn. Nhưng biết và hiểu các kỹ thuật thiết kế prototype, UX, UI sẽ là điểm đặc biệt ở bạn. Server Bạn nên có các kiến thức cơ bản về các hệ điều hành như Windows, Linux hoặc Apache,…</span></p><h3><span class=\"ql-size-large\">Hiểu về thiết kế Front-end</span></h3><p><span class=\"ql-size-large\">Hiện nay, các công nghệ Front-end hay được sử dụng nhất chính là HTML, CSS hay JavaScript,.. Thêm vào đó, sự am hiểu về User Experience cũng có thể giúp bạn trở thành một Full Stack Developer.</span></p><h3><span class=\"ql-size-large\">Quản lý phiên bản</span></h3><p><span class=\"ql-size-large\">Một Fullstack Developer luôn được đặt ra câu hỏi có biết sử dụng version control system, các kiến thức về Git cũng như các kiến thức quản lý liên quan.</span></p><h3><span class=\"ql-size-large\">Làm việc với API</span></h3><p><span class=\"ql-size-large\">Kiến thức về API cũng không thể thiếu trong các yêu cầu tuyển dụng đối với Fullstack Developer.</span></p><h3><span class=\"ql-size-large\">Một số kỹ năng cần thiết khác phải trang bị như:</span></h3><p><span class=\"ql-size-large\">Có thể viết các unit test</span></p><p><span class=\"ql-size-large\">Hiểu được cách xây dựng automation setting</span></p><p><span class=\"ql-size-large\">Hiểu về bảo mật</span></p><p><span class=\"ql-size-large\">Trang bị kiến thức về giải thuật, cấu trúc dữ liệu.</span></p><h3><span class=\"ql-size-large\">Kỹ năng mềm khác</span></h3><p><span class=\"ql-size-large\">Ngoài các kỹ năng chuyên môn ở trên, bạn cũng cần trang bị cho mình những kỹ năng mềm khác để có thể làm việc hiệu quả hơn như:</span></p><p><span class=\"ql-size-large\">Có tầm nhìn dài hạn</span></p><p><span class=\"ql-size-large\">Có khả năng giao tiếp</span></p><p><span class=\"ql-size-large\">Sáng tạo</span></p><p><span class=\"ql-size-large\">Phân tích hiệu quả</span></p><p><span class=\"ql-size-large\">Luôn muốn học hỏi</span></p><p><span class=\"ql-size-large\">Quản lý thời gian hiệu quả</span></p><p><span class=\"ql-size-large\">Giải quyết vấn đề</span></p><p><span class=\"ql-size-large\">Có kỷ luật</span></p><h2><span class=\"ql-size-large\">Lời kết</span></h2><p><span class=\"ql-size-large\">Hy vọng rằng qua bài viết này, bạn đã có thể hiểu đúng về khái niệm fullstack là gì và đưa ra được định hướng cho bản thân mình, chúc các bạn thành công!</span></p>','2024-05-16 23:06:14.139000','Chắc hẳn hầu hết các bạn trong giới lập trình đều đã ít nhất một lần nghe đến từ “fullstack”. Tuy nhiên vẫn còn nhiều người hiểu...','fullstack-la-gi-can-ky-nang-gi-de-tro-thanh-fullstack-developer','https://res.cloudinary.com/dqnoopa0x/image/upload/v1715875576/wffnxyth7kuyfpcfpyo2.jpg','Fullstack là gì? Cần kỹ năng gì để trở thành fullstack developer?',2,6),(6,'<h1><strong class=\"ql-size-large\">Khoe chứng chỉ</strong></h1>','2024-05-19 19:34:12.200000','Khoe chứng chỉ','khoe-chung-chi','https://res.cloudinary.com/dqnoopa0x/image/upload/v1716122054/gvrso4l2zwapvos1ne0y.png','Khoe chứng chỉ',0,1),(8,'<p>123</p>','2024-05-29 17:47:46.308000','123','123','https://res.cloudinary.com/dqnoopa0x/image/upload/v1716979668/k08aohl4lkqqwnxf4axx.png','123',1,9);
/*!40000 ALTER TABLE `blog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categories` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `slug` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_t8o6pivur7nn124jehx7cygw5` (`name`),
  UNIQUE KEY `UK_oul14ho7bctbefv8jywp5v3i2` (`slug`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (1,'Lập trình devops','lap-trinh-devops'),(2,'Lập trình C++','lap-trinh-c'),(3,'Kiến thức cơ bản','kien-thuc-co-ban');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `certificate`
--

DROP TABLE IF EXISTS `certificate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `certificate` (
  `id` int NOT NULL AUTO_INCREMENT,
  `achieved_time` datetime(6) NOT NULL,
  `course_id` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK13t46rtyipt6ayvme7crsdvs4` (`course_id`),
  KEY `FKtnnj9ktwn18vtvap4yuptwxhg` (`user_id`),
  CONSTRAINT `FK13t46rtyipt6ayvme7crsdvs4` FOREIGN KEY (`course_id`) REFERENCES `courses` (`id`),
  CONSTRAINT `FKtnnj9ktwn18vtvap4yuptwxhg` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `certificate`
--

LOCK TABLES `certificate` WRITE;
/*!40000 ALTER TABLE `certificate` DISABLE KEYS */;
INSERT INTO `certificate` VALUES (1,'2024-05-16 14:38:59.080000',3,1),(2,'2024-05-16 15:57:03.936000',3,2),(3,'2024-05-16 23:02:52.991000',3,6),(4,'2024-05-17 10:20:12.279000',3,7),(5,'2024-05-19 19:19:51.991000',3,9);
/*!40000 ALTER TABLE `certificate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chapters`
--

DROP TABLE IF EXISTS `chapters`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chapters` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `orders` int NOT NULL,
  `course_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6h1m0nrtdwj37570c0sp2tdcs` (`course_id`),
  CONSTRAINT `FK6h1m0nrtdwj37570c0sp2tdcs` FOREIGN KEY (`course_id`) REFERENCES `courses` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chapters`
--

LOCK TABLES `chapters` WRITE;
/*!40000 ALTER TABLE `chapters` DISABLE KEYS */;
INSERT INTO `chapters` VALUES (1,'Giới thiệu',0,1),(2,'Window Terminal & WSL',1,1),(3,'Các lệnh Linux cơ bản',2,1),(4,'Chạy dự án React, Node, Laravel',3,1),(5,'Deploy dự án với Server thật',4,1),(6,'Giới thiệu',0,3);
/*!40000 ALTER TABLE `chapters` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contests`
--

DROP TABLE IF EXISTS `contests`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contests` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NOT NULL,
  `enabled` bit(1) NOT NULL,
  `period` int NOT NULL,
  `title` varchar(150) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_g1nm0irnykh69s8m1fdu9fg64` (`title`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contests`
--

LOCK TABLES `contests` WRITE;
/*!40000 ALTER TABLE `contests` DISABLE KEYS */;
INSERT INTO `contests` VALUES (1,'2024-05-16 13:56:57.219000',_binary '',10,'HTML & CSS');
/*!40000 ALTER TABLE `contests` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_informations`
--

DROP TABLE IF EXISTS `course_informations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course_informations` (
  `id` int NOT NULL AUTO_INCREMENT,
  `type` enum('TARGET','REQUIREMENT') DEFAULT NULL,
  `value` text NOT NULL,
  `course_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4d564c488r0guro83j36wpyts` (`course_id`),
  CONSTRAINT `FK4d564c488r0guro83j36wpyts` FOREIGN KEY (`course_id`) REFERENCES `courses` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_informations`
--

LOCK TABLES `course_informations` WRITE;
/*!40000 ALTER TABLE `course_informations` DISABLE KEYS */;
INSERT INTO `course_informations` VALUES (12,'TARGET','Xây dựng ứng dụng đơn giản',2),(13,'TARGET','Hiểu lập trình hướng đối tượng',2),(14,'TARGET','Tham gia dự án phần mềm nhỏ',2),(15,'TARGET','Tạo nền tảng học lập trình nâng cao',2),(16,'REQUIREMENT','Có kiến thức cơ bản về lập trình',2),(17,'REQUIREMENT','Sử dụng thành thạo môi trường phát triển C++',2),(18,'TARGET','Cách học lập trình hiệu quả hơn',3),(19,'TARGET','Cách tra cứu tài liệu nhanh và chính xác hơn',3),(20,'REQUIREMENT','Chỉ cần có thiết bị thông minh',3),(21,'TARGET','Biết cách cài đặt và tùy biến Windows Terminal',1),(22,'TARGET','Thành thạo sử dụng các lệnh Linux/Ubuntu',1),(23,'TARGET','Biết cài đặt PHP 7.4 và MariaDB trên Ubuntu 20.04',1),(24,'TARGET','Biết sử dụng Windows Subsystem for Linux',1),(25,'TARGET','Biết cài đặt Node và tạo dự án ReactJS/ExpressJS',1),(26,'TARGET',' Hiểu về Ubuntu và biết tự cài đặt các phần mềm khác',1),(27,'REQUIREMENT','Máy tính kết nối internet (từ Windows 10 trở lên)',1),(28,'REQUIREMENT','Đã từng làm việc với Terminal, hiểu Terminal là gì và để làm gì',1),(29,'REQUIREMENT','Ý thức cao, trách nhiệm cao trong việc tự học. Thực hành lại sau mỗi bài học',1),(30,'REQUIREMENT','Bạn không cần biết gì hơn nữa, trong khóa học tôi sẽ chỉ cho bạn những gì bạn cần phải biết',1);
/*!40000 ALTER TABLE `course_informations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `courses`
--

DROP TABLE IF EXISTS `courses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `courses` (
  `id` int NOT NULL AUTO_INCREMENT,
  `description` text NOT NULL,
  `discount` float NOT NULL,
  `is_enabled` bit(1) DEFAULT NULL,
  `is_finished` bit(1) DEFAULT NULL,
  `is_published` bit(1) DEFAULT NULL,
  `price` int NOT NULL,
  `published_at` datetime(6) DEFAULT NULL,
  `slug` varchar(70) NOT NULL,
  `student_count` int DEFAULT NULL,
  `thumbnail` varchar(100) NOT NULL,
  `title` varchar(60) NOT NULL,
  `category_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_rbsoo34muf0lcryn4q2i0h3vc` (`slug`),
  UNIQUE KEY `UK_pag0ngrmsyx23ii8bnu9k0438` (`title`),
  KEY `FK72l5dj585nq7i6xxv1vj51lyn` (`category_id`),
  CONSTRAINT `FK72l5dj585nq7i6xxv1vj51lyn` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `courses`
--

LOCK TABLES `courses` WRITE;
/*!40000 ALTER TABLE `courses` DISABLE KEYS */;
INSERT INTO `courses` VALUES (1,'Sở hữu một Terminal hiện đại, mạnh mẽ trong tùy biến và học cách làm việc với Ubuntu là một bước quan trọng trên con đường trở thành một Web Developer.',0.9,_binary '',_binary '\0',_binary '',200000,'2024-05-16 23:30:30.500000','lam-viec-voi-terminal-va-ubuntu',11,'https://res.cloudinary.com/dqnoopa0x/image/upload/v1715779152/mqrk8jar2wirhpapmfx4.png','Làm việc với Terminal và Ubuntu',1),(2,'Khóa học lập trình C++ từ cơ bản tới nâng cao dành cho người mới bắt đầu. Mục tiêu của khóa học này nhằm giúp các bạn nắm được các khái niệm căn cơ của lập trình, giúp các bạn có nền tảng vững chắc để chinh phục con đường trở thành một lập trình viên.',0.5,_binary '',_binary '\0',_binary '\0',500000,NULL,'lap-trinh-c-co-ban-nang-cao',0,'https://res.cloudinary.com/dqnoopa0x/image/upload/v1715841090/rnrjoz7yeu2feoy6f9jh.png','Lập trình C++ cơ bản, nâng cao',2),(3,'Đây là kiến thức nhập môn nói về cách học như thế nào cho hiệu quả và cách tra cứu tài liệu.',0.5,_binary '',_binary '',_binary '',4000,'2024-05-16 14:03:35.362000','kien-thuc-nhap-mon',8,'https://res.cloudinary.com/dqnoopa0x/image/upload/v1715841840/uxpm1kif9uzpcteeisuk.png','Kiến thức nhập môn',3);
/*!40000 ALTER TABLE `courses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feed_back`
--

DROP TABLE IF EXISTS `feed_back`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `feed_back` (
  `id` int NOT NULL AUTO_INCREMENT,
  `content` text NOT NULL,
  `email` varchar(60) NOT NULL,
  `full_name` varchar(45) NOT NULL,
  `phone_number` varchar(11) NOT NULL,
  `status` enum('NEW','SENT') DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feed_back`
--

LOCK TABLES `feed_back` WRITE;
/*!40000 ALTER TABLE `feed_back` DISABLE KEYS */;
INSERT INTO `feed_back` VALUES (1,'Tôi cần liên hệ v ới admin về việc fix lỗi','norowak354@mfyax.com','Phạm Ngọc Viễn Đông','0813535314','SENT'),(2,'Địa chỉ mình ở đâu v ạ','duyangialai123@gmail.com','Trịnh Duy An','0979636194','SENT');
/*!40000 ALTER TABLE `feed_back` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lesson`
--

DROP TABLE IF EXISTS `lesson`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lesson` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `lesson_type` enum('VIDEO','QUIZ','TEXT') DEFAULT NULL,
  `name` varchar(100) NOT NULL,
  `orders` int NOT NULL,
  `chapter_id` int DEFAULT NULL,
  `text_id` int DEFAULT NULL,
  `video_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_22txxbpxqvovgp05l3r3g1k9c` (`text_id`),
  UNIQUE KEY `UK_4j46wplmpx5lr71ugp7engt51` (`video_id`),
  KEY `FKbmvsfsb079cs19554dfl2m1id` (`chapter_id`),
  CONSTRAINT `FK48my8s72slh0uu28lu3svaqk2` FOREIGN KEY (`text_id`) REFERENCES `text_lessons` (`id`),
  CONSTRAINT `FK9xcabosqsbjf9eo79xye6xv2c` FOREIGN KEY (`video_id`) REFERENCES `video` (`id`),
  CONSTRAINT `FKbmvsfsb079cs19554dfl2m1id` FOREIGN KEY (`chapter_id`) REFERENCES `chapters` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lesson`
--

LOCK TABLES `lesson` WRITE;
/*!40000 ALTER TABLE `lesson` DISABLE KEYS */;
INSERT INTO `lesson` VALUES (1,'2024-05-15 22:20:35.118000','VIDEO','Giới thiệu Windows Terminal & WSL',1,1,NULL,1),(2,'2024-05-16 12:16:56.453000','VIDEO','Cài đặt Windows Terminal',1,2,NULL,2),(3,'2024-05-16 12:28:18.317000','QUIZ','Ôn tập cài đặt Windows Terminal',2,2,NULL,NULL),(4,'2024-05-16 13:21:50.241000','VIDEO','Cài Đặt Ubuntu với WSL 1',3,2,NULL,3),(5,'2024-05-16 13:22:46.997000','QUIZ','Ôn tập cài đặt Ubuntu với WSL 1',4,2,NULL,NULL),(6,'2024-05-16 13:47:03.429000','VIDEO','Bài học dạng video',1,6,NULL,4),(7,'2024-05-16 13:49:25.966000','TEXT','Bài học dạng văn bản',2,6,1,NULL),(8,'2024-05-16 13:51:04.685000','QUIZ','Bài học dạng câu hỏi',3,6,NULL,NULL),(9,'2024-05-16 14:32:56.454000','TEXT','Giới thiệu bài học',2,1,2,NULL),(11,'2024-06-05 21:21:33.661000','VIDEO','bài mới của chương 3',1,3,NULL,5);
/*!40000 ALTER TABLE `lesson` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notes`
--

DROP TABLE IF EXISTS `notes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `content` text NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `current_times` time(6) NOT NULL,
  `lesson_id` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5c3nije94u992vpo71887270m` (`lesson_id`),
  KEY `FKechaouoa6kus6k1dpix1u91c` (`user_id`),
  CONSTRAINT `FK5c3nije94u992vpo71887270m` FOREIGN KEY (`lesson_id`) REFERENCES `lesson` (`id`),
  CONSTRAINT `FKechaouoa6kus6k1dpix1u91c` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notes`
--

LOCK TABLES `notes` WRITE;
/*!40000 ALTER TABLE `notes` DISABLE KEYS */;
INSERT INTO `notes` VALUES (1,'<p><strong class=\"ql-size-large\">WLS</strong></p>','2024-05-16 16:12:41.217000','00:01:05.000000',1,3),(2,'<p>123</p>','2024-05-17 10:16:59.907000','00:00:52.000000',1,7),(3,'<p>Ghi chú ở đây</p><p><br></p>','2024-05-19 19:14:08.741000','00:00:20.000000',1,9),(4,'<h1><strong>ghi chú gì đây huy sủa</strong></h1>','2024-06-05 21:13:59.975000','00:00:19.000000',2,9);
/*!40000 ALTER TABLE `notes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_time` datetime(6) NOT NULL,
  `total_price` int NOT NULL,
  `course_id` int DEFAULT NULL,
  `customer_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK68snkj0g5gsjxllhjc3v5lm0r` (`course_id`),
  KEY `FKsjfs85qf6vmcurlx43cnc16gy` (`customer_id`),
  CONSTRAINT `FK68snkj0g5gsjxllhjc3v5lm0r` FOREIGN KEY (`course_id`) REFERENCES `courses` (`id`),
  CONSTRAINT `FKsjfs85qf6vmcurlx43cnc16gy` FOREIGN KEY (`customer_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (5,'2024-05-16 14:37:25.157000',2000,3,1),(6,'2024-05-16 15:35:07.598000',20000,1,2),(8,'2024-05-16 15:55:28.747000',2000,3,2),(9,'2024-05-16 16:10:10.255000',20000,1,3),(10,'2024-05-16 20:21:26.065000',20000,1,4),(11,'2024-05-16 20:28:53.217000',2000,3,4),(12,'2024-05-16 22:59:59.805000',20000,1,6),(13,'2024-05-16 23:01:56.006000',2000,3,6),(14,'2024-05-16 23:26:10.667000',2000,3,7),(15,'2024-05-17 10:13:42.108000',20000,1,7),(16,'2024-05-19 19:13:11.395000',20000,1,9),(17,'2024-05-19 19:19:12.555000',2000,3,9),(18,'2024-05-31 22:53:51.103000',20000,1,1);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `questions_answers`
--

DROP TABLE IF EXISTS `questions_answers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `questions_answers` (
  `id` int NOT NULL AUTO_INCREMENT,
  `content` text NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `lesson_id` int DEFAULT NULL,
  `parent_id` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKf2cs1p5oxux1pjb2a19hwonx5` (`lesson_id`),
  KEY `FK83wnbesjvrx50d1ykcmof0hba` (`parent_id`),
  KEY `FKje750h01n2nvmvb566i8gl32m` (`user_id`),
  CONSTRAINT `FK83wnbesjvrx50d1ykcmof0hba` FOREIGN KEY (`parent_id`) REFERENCES `questions_answers` (`id`),
  CONSTRAINT `FKf2cs1p5oxux1pjb2a19hwonx5` FOREIGN KEY (`lesson_id`) REFERENCES `lesson` (`id`),
  CONSTRAINT `FKje750h01n2nvmvb566i8gl32m` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `questions_answers`
--

LOCK TABLES `questions_answers` WRITE;
/*!40000 ALTER TABLE `questions_answers` DISABLE KEYS */;
INSERT INTO `questions_answers` VALUES (2,'<p>Bài học dễ hiểu quá ạ hihi</p>','2024-05-16 15:37:05.547000',1,NULL,2),(3,'<p>Công nhận dễ hiểu thật cậu ạ</p>','2024-05-16 16:11:55.607000',1,2,3),(4,'<p>Khoá này sẽ giúp được rất nhiều trong tương lại đây</p>','2024-05-16 20:30:29.096000',1,NULL,4),(5,'<p>Nhất trí luôn cậu ơi</p>','2024-05-16 20:32:01.701000',1,2,4),(6,'<p>123</p>','2024-05-17 10:17:15.835000',1,NULL,7),(7,'<p>quá hay luôn</p>','2024-05-19 19:14:27.563000',1,2,9),(8,'<p>hỏi đáp gì</p>','2024-06-05 21:14:29.795000',1,NULL,9),(9,'<p>456</p>','2024-06-05 21:14:35.233000',1,6,9);
/*!40000 ALTER TABLE `questions_answers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quizzes`
--

DROP TABLE IF EXISTS `quizzes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `quizzes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `question` varchar(200) NOT NULL,
  `quiz_type` enum('ONE_CHOICE','MULTIPLE_CHOICE','PERFORATE') DEFAULT NULL,
  `contest_id` int DEFAULT NULL,
  `lesson_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKpjwnb7wdueyg306xhxshr36u2` (`contest_id`),
  KEY `FKd5pcqfxircwg00k1rtn1d14hf` (`lesson_id`),
  CONSTRAINT `FKd5pcqfxircwg00k1rtn1d14hf` FOREIGN KEY (`lesson_id`) REFERENCES `lesson` (`id`),
  CONSTRAINT `FKpjwnb7wdueyg306xhxshr36u2` FOREIGN KEY (`contest_id`) REFERENCES `contests` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quizzes`
--

LOCK TABLES `quizzes` WRITE;
/*!40000 ALTER TABLE `quizzes` DISABLE KEYS */;
INSERT INTO `quizzes` VALUES (1,'Muốn đóng một tab trên `Window Terminal` chúng ta dùng phím tắt nào?','ONE_CHOICE',NULL,3),(2,'`WSL` viết tắt của từ gì?','ONE_CHOICE',NULL,5),(3,'Câu hỏi dạng một đáp án','ONE_CHOICE',NULL,8),(4,'Câu hỏi dạng nhiều đáp án','ONE_CHOICE',NULL,8),(5,'Câu hỏi dạng đục lỗ: ____','PERFORATE',NULL,8),(6,'Để khai báo một phần tử điều khiển khi nhấn vào sẽ gửi thông tin của form đi ta sử dụng thẻ:','ONE_CHOICE',1,NULL),(7,'Khi sử dụng thuộc tính CSS `display`, những giá trị nào sau đây là hợp lệ?','MULTIPLE_CHOICE',1,NULL),(8,'Thuộc tính HTML được sử dụng để đặt URL của một trang web trong thẻ <a> là _____','PERFORATE',1,NULL),(11,'câu hỏi 4123','MULTIPLE_CHOICE',1,NULL);
/*!40000 ALTER TABLE `quizzes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `record_detail`
--

DROP TABLE IF EXISTS `record_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `record_detail` (
  `id` int NOT NULL AUTO_INCREMENT,
  `content_perforate` varchar(255) DEFAULT NULL,
  `quiz_id` int DEFAULT NULL,
  `record_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKi492cvghnbsnywv4a9wbc9lk6` (`quiz_id`),
  KEY `FKhhgtssn1mgxccai4l2xwhj2fj` (`record_id`),
  CONSTRAINT `FKhhgtssn1mgxccai4l2xwhj2fj` FOREIGN KEY (`record_id`) REFERENCES `records` (`id`),
  CONSTRAINT `FKi492cvghnbsnywv4a9wbc9lk6` FOREIGN KEY (`quiz_id`) REFERENCES `quizzes` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `record_detail`
--

LOCK TABLES `record_detail` WRITE;
/*!40000 ALTER TABLE `record_detail` DISABLE KEYS */;
INSERT INTO `record_detail` VALUES (1,NULL,6,1),(2,NULL,7,1),(3,'href',8,1),(4,NULL,6,2),(5,NULL,7,2),(6,'hreff',8,2),(7,NULL,6,3),(8,NULL,7,3),(9,'href',8,3),(10,NULL,6,4),(11,NULL,7,4),(12,'hello world',8,4),(13,NULL,6,5),(14,NULL,7,5),(15,'href',8,5),(16,NULL,6,6),(17,NULL,7,6),(18,'tôi không biết',8,6),(19,NULL,6,7),(20,NULL,7,7),(21,'href',8,7),(22,NULL,6,8),(23,NULL,7,8),(24,'href',8,8),(25,NULL,6,9),(26,NULL,7,9),(27,'href',8,9),(28,NULL,6,10),(29,NULL,7,10),(30,'123',8,10),(31,NULL,6,11),(32,'href',8,11),(33,NULL,7,11);
/*!40000 ALTER TABLE `record_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `records`
--

DROP TABLE IF EXISTS `records`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `records` (
  `id` int NOT NULL AUTO_INCREMENT,
  `grade` float NOT NULL,
  `joined_at` datetime(6) NOT NULL,
  `period` int NOT NULL,
  `total_answer_correct` float NOT NULL,
  `contest_id` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKt6px0rn2n2xghn9mt497emlt5` (`contest_id`),
  KEY `FK6p95uajgka0j0dc9vlbjw1sf1` (`user_id`),
  CONSTRAINT `FK6p95uajgka0j0dc9vlbjw1sf1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKt6px0rn2n2xghn9mt497emlt5` FOREIGN KEY (`contest_id`) REFERENCES `contests` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `records`
--

LOCK TABLES `records` WRITE;
/*!40000 ALTER TABLE `records` DISABLE KEYS */;
INSERT INTO `records` VALUES (1,10,'2024-05-16 13:58:02.868000',25,3,1,1),(2,1.33,'2024-05-16 14:00:48.548000',12,0.4,1,1),(3,9.33,'2024-05-16 15:45:45.299000',65,2.8,1,2),(4,6,'2024-05-16 16:14:17.300000',14,1.8,1,3),(5,10,'2024-05-16 16:44:51.013000',35,3,1,3),(6,4,'2024-05-16 20:32:50.090000',20,1.2,1,4),(7,9.33,'2024-05-16 23:04:43.998000',45,2.8,1,6),(8,9.33,'2024-05-17 10:21:20.624000',16,2.8,1,7),(9,10,'2024-05-19 19:11:13.839000',13,3,1,9),(10,5.33,'2024-06-02 10:17:17.792000',14,1.6,1,1),(11,9.33,'2024-06-05 21:10:38.705000',11,2.8,1,10);
/*!40000 ALTER TABLE `records` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `records_answers`
--

DROP TABLE IF EXISTS `records_answers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `records_answers` (
  `record_detail_id` int NOT NULL,
  `answer_id` int NOT NULL,
  PRIMARY KEY (`record_detail_id`,`answer_id`),
  KEY `FKiq5c402iuxggxgxcyg27f2dyh` (`answer_id`),
  CONSTRAINT `FKiq5c402iuxggxgxcyg27f2dyh` FOREIGN KEY (`answer_id`) REFERENCES `answers` (`id`),
  CONSTRAINT `FKrgaqktogxv3bc8ehbxnqqfnwi` FOREIGN KEY (`record_detail_id`) REFERENCES `record_detail` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `records_answers`
--

LOCK TABLES `records_answers` WRITE;
/*!40000 ALTER TABLE `records_answers` DISABLE KEYS */;
INSERT INTO `records_answers` VALUES (4,17),(1,18),(7,18),(10,18),(13,18),(16,18),(19,18),(22,18),(25,18),(28,18),(31,18),(2,21),(5,21),(8,21),(11,21),(14,21),(17,21),(20,21),(23,21),(26,21),(29,21),(33,21),(2,22),(5,22),(8,22),(11,22),(14,22),(17,22),(20,22),(23,22),(26,22),(29,22),(33,22),(5,23),(11,23),(17,23),(23,23),(29,23),(33,23),(2,24),(5,24),(8,24),(11,24),(14,24),(23,24),(26,24),(29,24),(33,24),(2,25),(8,25),(11,25),(14,25),(20,25),(23,25),(26,25),(33,25),(2,26),(11,26),(14,26),(20,26),(23,26),(26,26),(29,26),(33,26);
/*!40000 ALTER TABLE `records_answers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `review` (
  `id` int NOT NULL AUTO_INCREMENT,
  `comment` text NOT NULL,
  `rating` int NOT NULL,
  `review_time` datetime(6) NOT NULL,
  `course_id` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKa76dd0r1lr1k0cuah145lib3r` (`course_id`),
  KEY `FK6cpw2nlklblpvc7hyt7ko6v3e` (`user_id`),
  CONSTRAINT `FK6cpw2nlklblpvc7hyt7ko6v3e` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKa76dd0r1lr1k0cuah145lib3r` FOREIGN KEY (`course_id`) REFERENCES `courses` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
INSERT INTO `review` VALUES (2,'Khoá học rẻ mà chất lượng khỏi bàn ✌️',5,'2024-05-16 16:10:47.724000',1,3),(3,'Khoá học đáng mua, đang sale nữa. Mình khuyên mua khoá này nha ',4,'2024-05-16 20:23:38.388000',1,4),(4,'Khoá học rất hay',5,'2024-05-16 23:00:30.935000',1,6),(5,'khoá  hay',5,'2024-05-17 10:14:09.941000',1,7),(6,'Khoá học hay quá',5,'2024-05-19 19:13:33.630000',1,9);
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_CUSTOMER');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `text_lessons`
--

DROP TABLE IF EXISTS `text_lessons`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `text_lessons` (
  `id` int NOT NULL AUTO_INCREMENT,
  `content` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `text_lessons`
--

LOCK TABLES `text_lessons` WRITE;
/*!40000 ALTER TABLE `text_lessons` DISABLE KEYS */;
INSERT INTO `text_lessons` VALUES (1,'<p><strong class=\"ql-size-large\"><u>Đây là bài học dạng văn bản</u></strong></p><p><br></p><blockquote><em class=\"ql-size-large\">Xin chào mọi người</em></blockquote>'),(2,'<h1><strong class=\"ql-size-large\">Giới thiệu bài học</strong></h1>');
/*!40000 ALTER TABLE `text_lessons` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `track_courses`
--

DROP TABLE IF EXISTS `track_courses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `track_courses` (
  `id` int NOT NULL AUTO_INCREMENT,
  `duration_video` time(6) DEFAULT NULL,
  `is_completed` bit(1) DEFAULT NULL,
  `is_current` bit(1) DEFAULT NULL,
  `is_unlock` bit(1) DEFAULT NULL,
  `chapter_id` int DEFAULT NULL,
  `course_id` int DEFAULT NULL,
  `lesson_id` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmtsrhuk85yo77lx55h9qoumqs` (`chapter_id`),
  KEY `FK5vaycdixusus3w4kod8gwr60k` (`course_id`),
  KEY `FKmav0ebl2kucnmofpncotj1tgx` (`lesson_id`),
  KEY `FKikusfmwuj6n9sy36b9nvltv5q` (`user_id`),
  CONSTRAINT `FK5vaycdixusus3w4kod8gwr60k` FOREIGN KEY (`course_id`) REFERENCES `courses` (`id`),
  CONSTRAINT `FKikusfmwuj6n9sy36b9nvltv5q` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKmav0ebl2kucnmofpncotj1tgx` FOREIGN KEY (`lesson_id`) REFERENCES `lesson` (`id`),
  CONSTRAINT `FKmtsrhuk85yo77lx55h9qoumqs` FOREIGN KEY (`chapter_id`) REFERENCES `chapters` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `track_courses`
--

LOCK TABLES `track_courses` WRITE;
/*!40000 ALTER TABLE `track_courses` DISABLE KEYS */;
INSERT INTO `track_courses` VALUES (18,NULL,_binary '',_binary '\0',_binary '',6,3,6,1),(19,NULL,_binary '',_binary '\0',_binary '',6,3,7,1),(20,NULL,_binary '',_binary '\0',_binary '',6,3,8,1),(21,'00:00:02.000000',_binary '\0',_binary '',_binary '',1,1,1,2),(22,NULL,_binary '\0',_binary '\0',_binary '\0',1,1,9,2),(23,NULL,_binary '\0',_binary '\0',_binary '\0',2,1,2,2),(24,NULL,_binary '\0',_binary '\0',_binary '\0',2,1,3,2),(25,NULL,_binary '\0',_binary '\0',_binary '\0',2,1,4,2),(26,NULL,_binary '\0',_binary '\0',_binary '\0',2,1,5,2),(33,NULL,_binary '',_binary '\0',_binary '',6,3,6,2),(34,NULL,_binary '',_binary '\0',_binary '',6,3,7,2),(35,NULL,_binary '',_binary '\0',_binary '',6,3,8,2),(36,NULL,_binary '',_binary '\0',_binary '',1,1,1,3),(37,NULL,_binary '',_binary '\0',_binary '',1,1,9,3),(38,'00:00:00.000000',_binary '\0',_binary '',_binary '',2,1,2,3),(39,NULL,_binary '\0',_binary '\0',_binary '\0',2,1,3,3),(40,NULL,_binary '\0',_binary '\0',_binary '\0',2,1,4,3),(41,NULL,_binary '\0',_binary '\0',_binary '\0',2,1,5,3),(42,'00:00:22.000000',_binary '\0',_binary '',_binary '',1,1,1,4),(43,NULL,_binary '\0',_binary '\0',_binary '\0',1,1,9,4),(44,NULL,_binary '\0',_binary '\0',_binary '\0',2,1,2,4),(45,NULL,_binary '\0',_binary '\0',_binary '\0',2,1,3,4),(46,NULL,_binary '\0',_binary '\0',_binary '\0',2,1,4,4),(47,NULL,_binary '\0',_binary '\0',_binary '\0',2,1,5,4),(48,'00:00:00.000000',_binary '\0',_binary '',_binary '',6,3,6,4),(49,NULL,_binary '\0',_binary '\0',_binary '\0',6,3,7,4),(50,NULL,_binary '\0',_binary '\0',_binary '\0',6,3,8,4),(51,'00:00:39.000000',_binary '\0',_binary '',_binary '',1,1,1,6),(52,NULL,_binary '\0',_binary '\0',_binary '\0',1,1,9,6),(53,NULL,_binary '\0',_binary '\0',_binary '\0',2,1,2,6),(54,NULL,_binary '\0',_binary '\0',_binary '\0',2,1,3,6),(55,NULL,_binary '\0',_binary '\0',_binary '\0',2,1,4,6),(56,NULL,_binary '\0',_binary '\0',_binary '\0',2,1,5,6),(57,NULL,_binary '',_binary '\0',_binary '',6,3,6,6),(58,NULL,_binary '',_binary '\0',_binary '',6,3,7,6),(59,NULL,_binary '',_binary '\0',_binary '',6,3,8,6),(60,'00:00:00.000000',_binary '',_binary '\0',_binary '',6,3,6,7),(61,NULL,_binary '',_binary '\0',_binary '',6,3,7,7),(62,NULL,_binary '',_binary '\0',_binary '',6,3,8,7),(63,'00:00:49.000000',_binary '',_binary '',_binary '',1,1,1,7),(64,NULL,_binary '',_binary '',_binary '',1,1,9,7),(65,NULL,_binary '',_binary '',_binary '',2,1,2,7),(66,NULL,_binary '',_binary '\0',_binary '',2,1,3,7),(67,NULL,_binary '\0',_binary '',_binary '',2,1,4,7),(68,NULL,_binary '\0',_binary '\0',_binary '\0',2,1,5,7),(69,NULL,_binary '',_binary '\0',_binary '',1,1,1,9),(70,NULL,_binary '',_binary '\0',_binary '',1,1,9,9),(71,'00:00:00.000000',_binary '\0',_binary '',_binary '',2,1,2,9),(72,NULL,_binary '\0',_binary '\0',_binary '\0',2,1,3,9),(73,NULL,_binary '\0',_binary '\0',_binary '\0',2,1,4,9),(74,NULL,_binary '\0',_binary '\0',_binary '\0',2,1,5,9),(75,NULL,_binary '',_binary '\0',_binary '',6,3,6,9),(76,NULL,_binary '',_binary '\0',_binary '',6,3,7,9),(77,NULL,_binary '',_binary '\0',_binary '',6,3,8,9),(78,'00:00:32.000000',_binary '\0',_binary '',_binary '',1,1,1,1),(79,NULL,_binary '\0',_binary '\0',_binary '\0',1,1,9,1),(80,NULL,_binary '\0',_binary '\0',_binary '\0',2,1,2,1),(81,NULL,_binary '\0',_binary '\0',_binary '\0',2,1,3,1),(82,NULL,_binary '\0',_binary '\0',_binary '\0',2,1,4,1),(83,NULL,_binary '\0',_binary '\0',_binary '\0',2,1,5,1),(96,NULL,_binary '\0',_binary '\0',_binary '\0',3,1,11,2),(97,NULL,_binary '\0',_binary '\0',_binary '\0',3,1,11,3),(98,NULL,_binary '\0',_binary '\0',_binary '\0',3,1,11,4),(99,NULL,_binary '\0',_binary '\0',_binary '\0',3,1,11,6);
/*!40000 ALTER TABLE `track_courses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_time` datetime(6) NOT NULL,
  `email` varchar(30) NOT NULL,
  `enabled` bit(1) NOT NULL,
  `full_name` varchar(45) NOT NULL,
  `password` varchar(64) NOT NULL,
  `phone_number` varchar(11) NOT NULL,
  `photo` varchar(100) DEFAULT NULL,
  `reset_password_token` varchar(30) DEFAULT NULL,
  `username` varchar(45) NOT NULL,
  `verification_code` varchar(64) DEFAULT NULL,
  `role_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_6dotkott2kjsp8vw4d0m25fb7` (`email`),
  UNIQUE KEY `UK_9q63snka3mdh91as4io72espi` (`phone_number`),
  KEY `FKp56c1712k691lhsyewcssf40f` (`role_id`),
  CONSTRAINT `FKp56c1712k691lhsyewcssf40f` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'2024-03-08 22:45:31.933000','pezoiks1@gmail.com',_binary '','Phạm Ngọc Viễn Đông','$2a$10$.RX1MyQo6YqyAPv1KRWiLu8pnut6Bal/j4r08kd1YG/585pDqca8G','0813535314','https://res.cloudinary.com/dqnoopa0x/image/upload/v1712482876/ooozzfj7t7p1zokgonni.jpg',NULL,'PeZoi',NULL,1),(2,'2024-05-16 15:23:52.611000','duyangialai123@gmail.com',_binary '','Trịnh Duy An','$2a$10$cCpm1lq/68NA35hZY5Fm.emwE9NZCgbxDTZGnFPDBQGnyYGdOTTdW','0979636194','https://res.cloudinary.com/dqnoopa0x/image/upload/v1715849412/b4fqjje17y6s22wkswux.png',NULL,'DuyAn',NULL,2),(3,'2024-05-16 16:08:50.222000','norowak354@mfyax.com',_binary '','Nguyễn Tấn Dũng','$2a$10$u3.GIFNqKsb0vg7.ri4.bOy3mbv42ppNuGxG46F0KjV94pWQ.XJF2','0856325417','https://res.cloudinary.com/dqnoopa0x/image/upload/v1712482876/ooozzfj7t7p1zokgonni.jpg',NULL,'DungNgoo',NULL,2),(4,'2024-05-16 20:15:55.897000','libayo5976@nweal.com',_binary '','Nguyễn Thanh Trọng','$2a$10$x76My2IJ7UmXIBO5ZgkvROTFy1MHIbwQiNBf5tb4xhdcSq2tzsFA.','0963521487','https://res.cloudinary.com/dqnoopa0x/image/upload/v1712482876/ooozzfj7t7p1zokgonni.jpg',NULL,'TrongNgoo',NULL,2),(6,'2024-05-16 22:47:18.490000','sogilep487@losvtn.com',_binary '','Nguyễn Phương Nam','$2a$10$HvXKcehVJKcoBPXfNvGISeeODx0B5rS7ggyqqDtEamzJ5mvgQoOfy','0965478523','https://res.cloudinary.com/dqnoopa0x/image/upload/v1712482876/ooozzfj7t7p1zokgonni.jpg',NULL,'NamNguyen',NULL,2),(7,'2024-05-16 23:21:29.684000','welamit800@mfyax.com',_binary '','Lê Việt Hùng','$2a$10$2vrCrThQK0CN9okaPgPXjuNGUOLXKk6eDWFdsFaKQbcngYckeR2sS','0963521458','https://res.cloudinary.com/dqnoopa0x/image/upload/v1712482876/ooozzfj7t7p1zokgonni.jpg',NULL,'HungNgoo',NULL,2),(8,'2024-05-17 07:43:54.490000','todad29746@rencr.com',_binary '','Phạm Văn Đồng','$2a$10$HVwPAVXZZgHt.1QPGMxmgOE7KPZIkwiugsUPUPAApS3e8XFAreF7a','0865325698','https://res.cloudinary.com/dqnoopa0x/image/upload/v1712482876/ooozzfj7t7p1zokgonni.jpg',NULL,'VanDong',NULL,2),(9,'2024-05-19 19:10:11.307000','gecepe5350@qiradio.com',_binary '','Phạm Lân','$2a$10$K/T8lHNWCJ0BH5miQdwnRuXuSf.Ab48E37J.AsYjtvYM/aLpzksAm','0865321456','https://res.cloudinary.com/dqnoopa0x/image/upload/v1712482876/ooozzfj7t7p1zokgonni.jpg',NULL,'LanPham',NULL,2),(10,'2024-06-05 20:58:52.038000','ragay30467@fresec.com',_binary '','Nguyễn Gia Huy','$2a$10$JQUNbZxv3s64.6Gg1EZx0uArqKt277OciDENUrfHpDHho9rDVHp6q','0863255598','https://res.cloudinary.com/dqnoopa0x/image/upload/v1712482876/ooozzfj7t7p1zokgonni.jpg',NULL,'HuyNguNgoc',NULL,2);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `video`
--

DROP TABLE IF EXISTS `video`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `video` (
  `id` int NOT NULL AUTO_INCREMENT,
  `description` text NOT NULL,
  `duration` time(6) NOT NULL,
  `url` varchar(150) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `video`
--

LOCK TABLES `video` WRITE;
/*!40000 ALTER TABLE `video` DISABLE KEYS */;
INSERT INTO `video` VALUES (1,'','00:04:12.000000','https://res.cloudinary.com/dqnoopa0x/video/upload/v1715786427/swxwii04pqnrb2olowgy.mp4'),(2,'','00:20:57.000000','https://res.cloudinary.com/dqnoopa0x/video/upload/v1715836611/kszftfb7ugh5unsy3mse.mp4'),(3,'','00:11:16.000000','https://res.cloudinary.com/dqnoopa0x/video/upload/v1715840504/srhlfbj0oji88r8zjeoh.mp4'),(4,'<p><strong class=\"ql-size-large\">Đây là bài học dạng video</strong></p>','00:00:09.000000','https://res.cloudinary.com/dqnoopa0x/video/upload/v1715842020/yvnvlrg32pg2bonr9hbi.mp4'),(5,'','00:00:02.000000','https://res.cloudinary.com/dqnoopa0x/video/upload/v1717597291/gwxlhhukyywu2bavcl7h.mp4');
/*!40000 ALTER TABLE `video` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-20 12:18:03
