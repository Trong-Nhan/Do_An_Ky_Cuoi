Create Database SolarBook
GO
Use SolarBook
GO


Create Table tblCategory(
	Id int primary key identity,
	[Name] nvarchar(100) unique
)
GO

Create Table tblPublisher(
	Id int primary key identity,
	[Name] nvarchar(100)
)

GO

Create Table tblBook(
	Id int primary key identity,
	[Name] nvarchar(100),
	CategoryId int,
	Price float,
	SalePrice float Default(0),
	Author nvarchar(100),
	PublisherId int,
	PublishYear int,
	Picture nvarchar(255),
	Number int,
	[Description] ntext, 
	[Page] int, 
	Rating float,
	[Status] bit,

	Foreign Key (CategoryId) References tblCategory(Id),
	Foreign Key (PublisherId) References tblPublisher(Id)
)
GO

Create Table tblCity(
	Id int primary key identity,
	[Name] nvarchar(50)
)
GO

Create Table tblUser(
	Id int primary key identity, 
	[Name] nvarchar(150),
	Phone varchar(10),
	[Password] varchar(50),
	Email varchar(100),
	CityId int,
	[Address] text,
	role varchar(10),

	Foreign Key (CityId) References tblCity(Id)
)
GO

Create Table tblPayment(
	Id int primary key identity,
	[Name] nvarchar(150)
)
GO


Create Table tblOrder(
	Id int primary key identity,
	UserId int,
	OrderDate Date,
	BookId int,
	BookNumber int,
	TotalPrice float,
	CityId int,
	ShippingAddress ntext,
	ShippingPrice int,
	PaymentId int,
	Note nvarchar(255),
	CreatedDate Date,
	Status nvarchar(100)

	Foreign Key (UserId) References tblUser(Id),
	Foreign Key (CityId) References tblCity(Id),
	Foreign Key (PaymentId) References tblPayment(Id),
	Foreign Key (BookId) References tblBook(Id)
)
GO


Create Table tblCart(
	Id int primary key identity,
	UserId int,
	BookId int,
	BookCount int

	Foreign Key (UserId) References tblUser(Id),
	Foreign Key (BookId) References tblBook(Id)
)
go


Create Table tblNews(
	Id int primary key identity,
	[Name] nvarchar(255),
	[Description] nvarchar(255),
	Detail ntext,
	[Picture] nvarchar(255),
	CreatedDate Date
)

GO


Insert Into tblCity(Name) Values (N'Hà Nội'), (N'TP. Hồ Chí Minh'), (N'Đà Nẵng');
go

Insert Into tblUser(Name, Phone, Password, Email, CityId, Role) Values (N'Nhân Admin', '0974079806', '1234', 'nhanadmin@gmail.com', 1, 'admin')
Insert Into tblUser(Name, Phone, Password, Email, CityId, Role) Values (N'Nhân', '0974079806', '1234', 'nhan@gmail.com', 1, 'user')
go

Insert Into tblNews(Name, Description, Detail, Picture, CreatedDate) Values (N'Tin tức', N'Sách và bước ngoặt đánh dấu văn minh nhân loại', N'Sách ghi 
lại lịch sử và tri thức nhân loại nhưng để đạt đến bước phát triển đột phá trong xã hội, những tri thức ấy phải được truyền bá rộng rãi. Sách thời 
trước đắt, hiếm và thường chỉ dành cho giới quý tộc, học giả, tu sĩ nhưng công nghệ in cùng sản xuất hàng loạt đã đem tri thức tới mọi tầng lớp.
Phát minh về công nghệ in ép của Johann Gutenberg đã giúp thế giới có thể sản xuất sách với số lượng lớn, rẻ và nhanh chóng. Đây là điều dường như “bất
 khả thi” thời điểm bấy giờ, tạo ra một bước ngoặt lớn trong ngành xuất bản sách.
Trước Gutenberg, người Trung Quốc cũng đã phát minh ra phương pháp in mộc bản. Với cách làm này, người thợ khắc các nét chữ, hình vẽ lên một tấm gỗ. 
Phần có chữ sẽ nổi lên cao, phần không chữ được khoét lõm xuống. Khi in, người thợ phủ một lớp mực mỏng trên bề mặt tấm gỗ, sau đó đặt tờ giấy lên và 
dùng một thanh gỗ đã mài nhẵn gạt nhẹ phía trên tờ giấy.', N'/storage/emulated/0/Pictures/news_1.png','2006/12/12')
Insert Into tblNews(Name, Description, Detail, Picture, CreatedDate) Values (N'Tin tức', N'Đà Nẵng tăng cường văn hóa đọc', N'Thời gian qua, thành phố 
Đà Nẵng đã có nhiều hoạt động tăng cường văn hóa đọc nhằm thu hút người dân tham gia, phát triển văn hóa đọc trong đời sống.
Trong bối cảnh bùng nổ thông tin, thiết bị điện tử đã trở thành công cụ giải trí đầy sức hút, khiến cho lượng người đọc sách giảm mạnh theo thời gian. 
Tại thành phố Đà Nẵng, nhiều giải pháp lan tỏa văn hóa đọc đến với cộng đồng đã được triển khai và nhận được nhiều tín hiệu tích cực.',
 N'/storage/emulated/0/Pictures/news_2.png','2006/12/12')
Insert Into tblNews(Name, Description, Detail, Picture, CreatedDate) Values (N'Tin tức', N'Thư viện sách cộng đồng hơn 6000 đầu sách hay và mới tại
 The Wiselands Coffee', N'Đối với quý bạn đọc yêu thích sách, quý bạn đọc thấn thiết của Book365 hẳn không còn xa lạ với hệ thống Thư quán cafe sách 
 The Wislelands Coffee. Ít ai biết đến bên trong không gian xanh mát với nét cổ kính và hiện đại của The Wiselands Coffee là một thư viện sách với hàng 
 ngàn đầu sách phong phú tại quán. Đến đây ngoài tận hưởng những giây phút thư	giãn, quý bạn có thể đọc bất kỳ cuốn sách yêu thích nào.',
  N'/storage/emulated/0/Pictures/news_3.jpeg','2006/12/12')
Insert Into tblNews(Name, Description, Detail, Picture, CreatedDate) Values (N'Tin tức', N'Sách xưa "Tam tự kinh"',
 N'Khi con người ta vừa mới chào đời, thiên tính đều là thiện lương. Bản tính của con người tuy rằng gần giống nhau, không có khác biệt lơn,
  nhưng do hoàn cảnh sống sau này khác biệt, do nền giáo dục bất đồng, nên tập tính có sự khác biệt rất rõ. Nếu từ nhỏ không dạy dỗ cẩn thận, thì bản
   tính thiện lương trời sinh sẽ thay đổi. Giáo dục trẻ nhỏ, cần chú trọng nhất là chuyên tâm, như nhất.',
    N'/storage/emulated/0/Pictures/news_4.png','2006/12/12')
Insert Into tblNews(Name, Description, Detail, Picture, CreatedDate) Values (N'Tin tức', N'70 năm ngày truyền thống ngành xuất bản in và phát hành sách Việt Nam (10/10/2952-10/10/2022)',
N'Ngày 10/10/1952, Bác Hồ ký sắc lệnh 122/SL thành lập Nhà in Quốc gia Trung ương. Sắc lệnh ra đời có ý nghĩa to lớn, mở ra thời kỳ phát triển mới cho sự nghiệp xuất bản ở nước ta. Từ đó, ngày 10 tháng 10 trở thành ngày truyền thống của ngành Xuất bản - In - Phát hành sách Việt Nam.
Trong dòng chảy lịch sử, nghề làm sách, sau này phát triển thành ngành Xuất bản, đã xuất hiện ở Việt Nam khá sớm, từ triều Lý, cùng sự ra đời của làm giấy và khắc ván in gỗ. Trải qua nhiều thăng trầm, ngành Xuất bản, In và Phát hành đã không ngừng lớn mạnh và có nhiều đóng góp quan trọng vào sự nghiệp xây dựng và bảo vệ đất nước, trở thành một bộ phận quan trọng của nền văn hóa Việt Nam. Đến nay, sau 70 năm kể từ khi Bác Hồ ký Sắc lệnh số 122/SL thành lập Nhà in Quốc gia ngày 10/10/1952, ngành Xuất bản, In và Phát hành đang có bước chuyển mình mạnh mẽ.
Trong Thư chúc mừng của Tổng bí thư Nguyễn Phú Trọng gửi các cán bộ Ngành xuất bản In và Phát hành sách nhân dịp kỷ niệm 70 năm Ngày truyền thống của ngành 10/10/1954-10/10/2022, đồng chí có viết:
"Trong suốt 70 năm qua, các thế hệ cán bộ, viên chức, người lao động làm công tác xuất bản, in và phát hành sách đã luôn luôn đoàn kết, vượt qua mọi khó khăn, gian khổ, là những chiến sĩ tiên phong trên mặt trận văn hóa - tư tưởng, góp phần xứng đáng vào sự nghiệp đấu tranh giải phóng dân tộc, xây dựng và bảo vệ Tổ quốc”.
Nhân dịp này, Book365.vn xin gửi lời cảm ơn trân trọng đến những người làm công tác xuất bản, in và phát hành, những tác giả sách Việt Nam đã cống hiến và nỗ lực không ngừng nghỉ đem lại những tựa sách hay, kho tàng hữu ích cho bạn đọc khắp mọi miền!
Trong chuỗi hoạt động chào mừng và kỷ niệm 70 năm ngày truyền thống Ngành Xuất bản, In và Phát hành sách Việt Nam 10/10/2022, Ban Tuyên giáo Trung ương, Bộ Thông tin và Truyền thông, Hội Xuất bản Việt Nam đã phối hợp tổ chức Lễ kỷ niệm 70 năm Ngày truyền thống Ngành Xuất bản, In và Phát hành sách Việt Nam (10/10/1952 - 10/10/2022) và Gặp mặt, tuyên dương người làm xuất bản tiêu biểu.
Buổi lễ vinh dự được đón tiếp Đồng chí Võ Văn Thưởng, Ủy viên Bộ Chính trị, Thường trực Ban Bí thư; Đồng chí Nguyễn Trọng Nghĩa, Bí thư Trung ương Đảng, Trưởng Ban Tuyên giáo Trung ương.
Tham dự buổi lễ còn có các đồng chí lãnh đạo Ban Tuyên giáo Trung ương, Bộ Thông tin và Truyền thông, Hội Xuất bản Việt Nam; đại diện lãnh đạo các ban, bộ, ngành, đoàn thể, tổ chức Hội Trung ương, một số địa phương có nhà xuất bản; đại diện lãnh đạo các cơ sở đào tạo, cơ quan chủ quản nhà xuất bản, các nhà xuất bản, công ty in, phát hành sách, một số chuyên gia trong lĩnh vực xuất bản, in và phát hành sách và 86 đại biểu là những người làm xuất bản, in và phát hành sách tiêu biểu được tuyên dương, khen thưởng tại Lễ kỷ niệm.
Lễ kỷ niệm nhằm khẳng định sự đóng góp to lớn của Ngành Xuất bản, In và Phát hành sách Việt Nam trong 70 năm qua; ghi nhận công lao, cống hiến của đội ngũ cán bộ, biên tập viên, người làm xuất bản; tuyên dương, khen thưởng những người làm xuất bản tiêu biểu; thông qua hoạt động kỷ niệm, nâng cao vị thế Ngành Xuất bản, In và Phát hành sách Việt Nam, đồng thời phát huy vai trò, trách nhiệm của người làm xuất bản, in và phát hành sách trong công cuộc xây dựng và phát triển đất nước.
Tại lễ kỷ niệm, ban tổ chức đã tặng biểu trưng vinh danh cho 5 cán bộ lão thành, có nhiều thành tích, cống hiến cho ngành xuất bản, in và phát hành sách Việt Nam; tặng bằng khen cho 81 người làm xuất bản có thành tích nổi bật, đóng góp xuất sắc trong hoạt động xuất bản, in và phát hành sách thời gian qua. 
5 cán bộ lão thành được vinh danh gồm: ông Phan Khắc Hải - nguyên thứ trưởng Bộ Văn hóa - Thông tin, nguyên chủ tịch Hội Xuất bản Việt Nam nhiệm kỳ đầu tiên (2001 - 2006); ông Đinh Xuân Dũng - nguyên vụ trưởng Vụ Xuất bản, Ban Tư tưởng Văn hóa Trung ương (nay là Ban Tuyên giáo Trung ương); ông Lê Hoàng - phó chủ tịch Hội Xuất bản Việt Nam, nguyên giám đốc, tổng biên tập Nhà xuất bản Trẻ; ông Nguyễn Văn Dòng - chủ tịch Hiệp hội In Việt Nam và ông Huỳnh Văn Bé - Anh hùng Lao động thời kỳ đổi mới, nguyên giám đốc Công ty CP in Tổng hợp Cần Thơ.
81 người làm xuất bản được tặng bằng khen có 5 cán bộ lãnh đạo cục, vụ, phòng, sở quản lý công tác xuất bản, còn lại là những người trực tiếp làm xuất bản, thư viện, bao gồm cả những lãnh đạo các công ty tư nhân có tham gia vào làm xuất bản như Nhã Nam, Chibooks, Hương Trang, Omega Việt Nam, Sách Phương Nam, Tân Việt.
Trân trọng cảm ơn và tri ân những cán bộ hoạt động trong lĩnh vực xuất bản, in và phát hành!', N'/storage/emulated/0/Pictures/news_4.png','2006/12/12')
go

Insert Into tblCategory(Name) Values (N'Sách thiếu nhi'), (N'Sách dành cho giới trẻ'), (N'Sách Chính trị - Xã hội'), (N'Tủ sách gia đình'), (N'Sách Giáo khoa - Giáo trình'), (N'Sách ngoại ngữ'), 
(N'Sách Quản lý - Kinh tế'), (N'Sách Khoa học - Công nghệ'), (N'Sách Văn học - Nghệ thuật'), (N'Sách khác')
GO
Insert Into tblPublisher(Name) Values (N'NXB Tuổi Trẻ'), (N'NXB Thanh Hóa'), (N'NXB Lao Động - Xã Hội'), (N'NXB Hội Nhà Văn')
GO

SET IDENTITY_INSERT tblBook on
go

INSERT [dbo].[tblBook] ([Id], [Name], [CategoryId], [Price], [SalePrice], [Author], [PublisherId], [PublishYear], [Picture], [Number], [Description], [Page], [Rating], [Status]) VALUES (1, N'
Lập và quản lý dự án CNTT ', 1, 35000, 0, N'Trường Đào tạo', 1, 2017, N'/storage/emulated/0/Pictures/ImageBook/15a6c179550118ec5f8e5d629d08666c.jpeg', 200, N'Ngày nay, trong nền kinh tế tri thức, khoa học và công nghệ được nghiên cứu và phát triển theo nhiều hướng, ngành, lĩnh vực chuyên sâu khác nhau: trong đó, Công nghệ thông tin đang thực sự trở thành một lĩnh vực không thể thiếu trong sự phát triển chung của toàn xã hội. Nhà nước luôn quan tâm, dành một khoản ngân sách cho các dự án Công nghệ thông tin và mỗi ngành, mỗi đơn vị, cá nhân cần có sự đầu tư ứng dụng Công nghệ thông tin cho phù hợp với yêu cầu của mình. Tuy nhiên, để quản lý và triển khai dự án Công nghệ thông tin như thế nào để đạt hiệu quả cao luôn là câu hỏi thường trực đối với những người tham gia về dự án công nghệ thông tin. Trước nhu cầu thực tiễn đó; Trường Đào tạo, Bồi dưỡng cán bộ quản lý Thông tin và Truyền thông đã phối hợp với Nhà xuất bản Thông tin và Truyền thông xuất bản cuốn sách “Lập và quản lý dự án Công nghệ thông tin” giới thiệu đến bạn đọc.', 100, NULL, 1)
INSERT [dbo].[tblBook] ([Id], [Name], [CategoryId], [Price], [SalePrice], [Author], [PublisherId], [PublishYear], [Picture], [Number], [Description], [Page], [Rating], [Status]) VALUES (2, N'Thế giới còn đổi thay, nhưng tư tưởng Hồ Chí Minh sống mãi', 3, 70000, 0, N'Đại tướng Võ Nguyên Giáp', 1, 2018, N'/storage/emulated/0/Pictures/ImageBook/3105d91dbcf781c262f5962e668bf097.png', 200, N'Thế giới còn đổi thay nhưng tư tưởng Hồ Chí Minh sống mãi là cuốn sách tập hợp một số bài nghiên cứu đã được công bố của Đại tướng Võ Nguyên Giáp về tư tưởng Hồ Chí Minh. Cuốn sách có ý nghĩa quan trọng, đặc biệt là trong bối cảnh hiện nay - khi việc nghiên cứu, học tập và làm theo tư tưởng, đạo đức, phong cách Hồ Chí Minh theo chỉ thị của Bộ Chính trị tiếp tục được triển khai sâu rộng trong toàn Đảng, toàn dân và toàn quân. Sách do Nhà xuất bản Chính trị quốc gia Sự thật ấn hành.', 262, NULL, 1)
INSERT [dbo].[tblBook] ([Id], [Name], [CategoryId], [Price], [SalePrice], [Author], [PublisherId], [PublishYear], [Picture], [Number], [Description], [Page], [Rating], [Status]) VALUES (3, N'Hãy trỗi dậy, Việt Nam! Let''s rise, Vietnam!', 3, 100000, 95000, N'Vũ Minh Khương', 2, 2020, N'/storage/emulated/0/Pictures/ImageBook/3a77266454abad3a595e232ecee6b0ba.jpeg', 200, N'Sách về tình yêu và niềm tự hào Việt Nam', 588, NULL, 1)
INSERT [dbo].[tblBook] ([Id], [Name], [CategoryId], [Price], [SalePrice], [Author], [PublisherId], [PublishYear], [Picture], [Number], [Description], [Page], [Rating], [Status]) VALUES (4, N'Bullshit Jobs: Đời ngắn lắm, đừng làm việc vô nghĩa!', 1, 200000, 150000, N'David Graeber', 4, 2019, N'/storage/emulated/0/Pictures/ImageBook/4e0d3df20b56f4c66094a003cb53405d.jpeg', 200, N'BULLSHIT JOBS: ĐỜI NGẮN LẮM, ĐỪNG LÀM VIỆC VÔ NGHĨA!
Là một khảo cứu xuất sắc, Bullshit Jobs: Đời ngắn lắm, đừng làm việc vô nghĩa! chỉ ra sự tồn tại và tác hại xã hội của những công việc vô nghĩa, những “nghề thừa” quanh ta. Giáo sư David Graeber cho rằng hơn một nửa công việc hiện tại là vô nghĩa, và chúng gây ra những thiệt hại đáng kể về mặt đạo đức và tinh thần, tạo nên những vết sẹo trong tâm hồn con người. Nhưng, hầu như không có ai nói về vấn đề này cả! Ông mô tả và lý giải các vấn đề ẩn sâu bên dưới quan niệm và thái độ của con người đối với công việc: 
- Rất nhiều người dành cả cuộc đời để làm các công việc mà họ thầm tin rằng chúng thực sự không cần thiết. 
- Cứ như có ai đó đang tạo ra những công việc vô bổ chỉ nhằm mục đích bắt tất cả chúng ta phải làm việc.
- Tại sao có tình trạng khi một người vừa mới bắt đầu nói về niềm tự hào trong công việc thì người khác lại thầm nghĩ rằng công việc của mình lẽ ra không nên tồn tại?
 - Làm sao để con người có thể được tự do làm những công việc họ thật sự yêu thích trong đời? 
Bullshit Jobs: Đời ngắn lắm, đừng làm việc vô nghĩa! là một bài kiểm tra đầy kịch tính, kích thích tư duy về cuộc sống lao động của chúng ta.', 304, NULL, 1)
INSERT [dbo].[tblBook] ([Id], [Name], [CategoryId], [Price], [SalePrice], [Author], [PublisherId], [PublishYear], [Picture], [Number], [Description], [Page], [Rating], [Status]) VALUES (5, N'Tạo động lực - Tăng hiệu suất', 7, 160000, 96000, N'Adrian Furnham , Ian MacRae', 3, 2020, N'/storage/emulated/0/Pictures/ImageBook/655591fcf0c4534ccd7097f8ea2d3d05.png', 200, N'TẠO ĐỘNG LỰC - TĂNG HIỆU SUẤT
Có hay không có động lực sẽ ảnh hưởng đến quyết định hành động hay không-hành-động của mỗi người. Động lực chính là điểm khác biệt giữa trạng thái thụ động chỉ nghĩ trong đầu về việc cần làm và trạng thái chủ động thực sự bắt tay vào làm. Khi có động lực, bất kỳ ai cũng dễ dàng đạt được những thành tích xuất sắc hơn.
Khoa học và quá trình nghiên cứu về động lực, năng suất trong công việc giúp chúng ta biết cách tạo động lực cho mọi người, cách tăng lợi nhuận, năng suất cho công ty, giúp công ty trở thành một nơi làm việc tốt hơn cho nhân viên. Những người lãnh đạo sẵn sàng nắm bắt và thực hiện cải thiện sẽ tạo lợi thế cạnh tranh mạnh mẽ cho công ty.
Công việc không phải là một chiến trường với hai phe là thành tích của công ty và sức khỏe tâm sinh lý của nhân viên. Chúng ta không nhất thiết phải hy sinh sức khỏe và hạnh phúc cho năng suất và lợi nhuận. Những nhân viên có động lực, gắn kết với công ty chắc chắn làm việc hiệu quả hơn, sáng tạo hơn, năng suất hơn và cống hiến nhiều hơn cho công ty. Những nơi làm việc tốt nhất luôn tạo một văn hóa và môi trường thúc đẩy nhu cầu cống hiến toàn bộ tiềm năng của nhân viên cho công ty.
Hai tác giả Adrian Furnham và Ian MacRae đã xây dựng quyển sách Tạo động lực - Tăng hiệu suất một cách thú vị, cuốn hút, truyền cảm hứng, và hơn hết thật hữu ích. Đặc biệt, nếu công việc của bạn là quản lý và tạo động lực cho mọi người, bạn sẽ tìm thấy nhiều phần trong quyển sách rất thích hợp để áp dụng trong công việc.
Bên cạnh những câu chuyện về thành công – thất bại, chỉ ra những sai lầm – thiếu sót trong quản lý và nhiều vấn đề khác đang diễn ra trong môi trường công sở, Tạo động lực - Tăng hiệu suất còn ẩn chứa hai thông điệp xuyên suốt và tích cực:
1. Mọi người có thể thay đổi để trở nên tốt hơn: Hiệu suất làm việc của mỗi cá nhân có thể cải thiện và có nhiều phương pháp để phát triển động lực bên trong cũng như nâng cao hiệu suất tổng thể.
2. Công việc có thể vừa truyền cảm hứng vừa mang lại lợi nhuận: Bên cạnh những cải cách góp phần cải thiện môi trường làm việc, vẫn còn nhiều phương diện khác trong công việc cần cải tiến để trở nên hiệu quả hơn, nâng hiệu suất lên cao hơn, thu về nhiều lợi nhuận hơn để cả nhân viên lẫn công ty cùng hưởng lợi.', 336, NULL, 1)
INSERT [dbo].[tblBook] ([Id], [Name], [CategoryId], [Price], [SalePrice], [Author], [PublisherId], [PublishYear], [Picture], [Number], [Description], [Page], [Rating], [Status]) VALUES (6, N'Bí mật của nước', 1, 100000, 0, N'Masura Emoto', 2, 2021, N'/storage/emulated/0/Pictures/ImageBook/6c22a4dca7f8e779f157f7d790a94011.jpeg', 200, N'Sự tồn tại của nước là một lý do quan trọng làm trái đất trở nên khác biệt, biến nó thành 1 hành tinh xanh nơi mà sự sống hiện hữu và sinh sôi. Con người tiếp xúc với nước mỗi ngày để duy trì các hoạt động sống nhưng lại biết quá ít về nó, thậm chí mơ hồ và lãng quên về những đều kỳ diệu mà nước đã đang và sẽ mang lại cho họ. Những mối bận tâm với công việc, những sở thích cá nhân, các mối quan hệ xã hội xâm chiếm hầu hết quỹ thời gian của mỗi người. Và do vô tình hay cố ý, ta quên đi cách trân trọng và cảm nhận sâu sắc về những món quà bình dị mà mẹ thiên nhiên trao tặng.', 200, NULL, 1)
INSERT [dbo].[tblBook] ([Id], [Name], [CategoryId], [Price], [SalePrice], [Author], [PublisherId], [PublishYear], [Picture], [Number], [Description], [Page], [Rating], [Status]) VALUES (7, N'Kỹ năng thuyết trình', 2, 150000, 100000, N'ĐOÀN CHÍ THIỆN', 2, 2016, N'/storage/emulated/0/Pictures/ImageBook/89d9d394d0a0b4c113748eb07aca1833.jpeg', 200, N'Cuộc sống hiện đại đòi hỏi mỗi cá nhân phải không ngừng cập nhật giá trị và hoàn thiện bản thân mình. Kỹ năng thuyết trình là một trong các hình thức của giao tiếp nhằm truyền đạt thông tin của con người, đồng thời là kỹ năng mềm quan trọng, nhất thiết phải có đối với bất kỳ ai, đang làm ngành, nghề gì. Vấn đề đặt ra là làm sao để truyền đạt thông tin đến người khác để đạt được mục tiêu một cách hiệu quả nhất. Đây thực sự là một thử thách và khó khăn của nhiều người. Bởi vì, thuyết trình không chỉ đơn thuần là một kỹ năng mà còn là một nghệ thuật thu phục nhân tâm, tạo động lực cho người nghe và là con đường thăng tiến trong công việc. Với niềm say mê, tâm huyết và kinh nghiệm thực tế của bản thân, Thạc sỹ Đoàn Chí Thiện – Đại học Đà Nẵng đã biên soạn cuốn sách “Kỹ năng thuyết trình”. Cuốn sách mang tính thực tiễn và sinh động, được tham khảo tư liệu của nhiều cá nhân, đơn vị trong và ngoài nước. Nội dung cuốn sách gồm 5 chương, cụ thể như sau: Chương 1: Tổng quan về kỹ năng thuyết trình; Chương 2: Xây dựng nội dung thuyết trình; Chương 3: Trình bày bài thuyết trình hiệu quả nhất; Chương 4: Những điều nên tránh khi thuyết trình; Chương 5: Để kết thúc bài thuyết trình ấn tượng. Với phương châm mang tính thực tế, tâm lý và khoa học, cuốn sách sẽ góp phần làm cho các bạn đọc, đặc biệt là các bạn sinh viên sẽ tự tin hơn, mạnh dạn hơn khi thuyết trình một vấn đề trước đám đông, nhằm hình thành nên một trong những kỹ năng mềm thiết thực cho học tập và công việc.', 300, NULL, 0)
INSERT [dbo].[tblBook] ([Id], [Name], [CategoryId], [Price], [SalePrice], [Author], [PublisherId], [PublishYear], [Picture], [Number], [Description], [Page], [Rating], [Status]) VALUES (8, N'Nghĩ thông suốt, đời nhẹ như mây', 1, 150000, 90000, N'Lysa TerKeurst', 4, 2019, N'/storage/emulated/0/Pictures/ImageBook/999543067b910b38e220845db08cfac2.jpeg', 200, N'Nghĩ thông suốt, đời nhẹ như mây
Kẻ thù luôn muốn chúng ta cảm thấy bị từ chối, bị bỏ rơi và cô đơn...
Trong Nghĩ thông suốt, đời nhẹ như mây, tác giả Lysa TerKeurst chia sẻ những trải nghiệm cá nhân sâu sắc của mình về sự từ chối — từ nhận định về người phụ nữ có thân hình săn chắc hoàn hảo một mình một góc cho đến tuổi thơ vô cùng đau đớn bị cha bỏ rơi. Lysa TerKeurst tập trung vào để kiểm tra một cách trung thực gốc rễ của sự từ chối, cũng như khả năng sự từ chối có thể đầu độc các mối quan hệ từ trong ra ngoài, bao gồm cả mối quan hệ của chúng ta với Chúa.
Với sự am hiểu sâu rộng về Kinh thánh, bằng một đức tính trung thực dễ bị tổn thương nhưng không kém phần hóm hỉnh, Lysa TerKeurst sẽ giúp bạn:
• NGỪNG CẢM THẤY BỊ BỎ RƠI bằng cách tin rằng ngay cả khi bạn bị người khác coi thường, bạn vẫn được Chúa lựa chọn.
• THAY ĐỔI KHUYNH HƯỚNG SUY SỤP hoặc kiểm soát hành động của người khác bằng cách dựa vào lòng tôn kính Chúa để xử lý tổn thương của bạn.
• BIẾT CHÍNH XÁC NHỮNG GÌ CẦN CẦU NGUYỆN trong mười ngày tới để ổn định tâm hồn và khôi phục sự tự tin của bạn khi bị từ chối.
• VƯỢT QUA HAI NỖI SỢ HÃI CỐT LÕI nuôi dưỡng sự bất an của bạn bằng cách am hiểu bí mật của sự sở hữu.
Nghĩ thông suốt, đời nhẹ như mây nhắc nhở rằng chúng ta đã được định sẵn cho một tình yêu không bao giờ có thể bị suy giảm, hoen ố, lung lay hay chiếm đoạt - một tình yêu không bị chối từ.', 304, NULL, 1)
INSERT [dbo].[tblBook] ([Id], [Name], [CategoryId], [Price], [SalePrice], [Author], [PublisherId], [PublishYear], [Picture], [Number], [Description], [Page], [Rating], [Status]) VALUES (9, N'Thời kỳ hậu Corona: Luôn có cơ hội trong khủng hoảng', 1, 280000, 150000, N'Scott Galloway', 2, 2022, N'/storage/emulated/0/Pictures/ImageBook/a566ddba96cf3ab7d9936dd64153b056.png', 200, N'THỜI KỲ HẬU CORONA
Diễn biến của thời cuộc là bất trắc. Trong khủng hoảng, nguy và cơ tồn tại song hành nhưng quan trọng là chúng tác động đến mọi người theo cách khác nhau:
“Nhóm người có thu nhập thấp hơn và người da màu có nguy cơ nhiễm bệnh cao hơn và khả năng bệnh có thể trở nặng cao gấp đôi so với những người thuộc các hộ gia đình có thu nhập cao hơn. Đối với những người giàu có, họ càng có thêm thời gian dành cho gia đình, cho Netflix, có thêm tiền tiết kiệm và giá trị chứng khoán đầu tư đều tăng vì quãng đường đi làm và chi phí đều giảm”.
Đây là thời kỳ mà người giàu sẽ giàu hơn và người nghèo lại khánh kiệt đi. Khoảng cách giàu nghèo càng cách biệt kéo theo các hệ lụy của bất bình đẳng, sự công phẫn và chia rẽ xã hội sâu sắc.
“Đại dịch đã làm lộ ra một thế hệ có toàn là những lựa chọn kém cỏi và đẩy nhanh hậu quả của những lựa chọn đó. Vẫn là bài ca con cá: Kẻ giàu càng giàu thêm”.
Dịch Covid-19 đã đưa chúng ta vào một kỷ nguyên mới, với một xã hội không có gì khác ngoài thách thức và thách thức. Một con virus với kích thước chỉ bằng 1/400 sợi tóc đã tóm được một quả cầu nặng 13 tỷ triệu triệu tấn và khiến quả cầu này quay nhanh gấp 10 lần. Chúng ta phải làm gì để đưa quả cầu đó trở lại quỹ đạo bình thường?
LUÔN CÓ CƠ HỘI TRONG KHỦNG HOẢNG
Với lối viết lôi cuốn, liên tục đan xen giữa thực tế, lý thuyết và lịch sử, Scott Galloway đã đem đến cho độc giả một cái nhìn sâu sắc về đại dịch Covid-19 đang diễn ra thông qua một lăng kính đầy màu sắc. Tác giả đã lột tả những mặt trái và khiếm khuyết về kinh tế-xã hội mà trước giờ chúng ta không nhận ra khi thế giới chưa xuất hiện đại dịch Covid-19 quái ác. Với Thời kỳ hậu Corona: Luôn có cơ hội trong khủng hoảng, độc giả sẽ thấy rõ hơn cách đại dịch ảnh hưởng và thay đổi môi trường kinh doanh nhiều như thế nào.
Thời kỳ hậu Corona: Luôn có cơ hội trong khủng hoảng là nỗ lực của tác giả Scott Galloway để nhìn xa hơn cái hiện tại chưa từng có của chúng ta và dự đoán tương lai bằng cách tạo ra nó, đối thoại và phân tích nó nhằm đưa ra các giải pháp tốt hơn. Khi đại dịch Covid-19 được kiểm soát, những khác biệt gì sẽ xảy ra trong việc kinh doanh, trong nền giáo dục và trong thế giới của chúng ta? Liệu nó sẽ nhân văn hơn và thịnh vượng hơn? Chúng ta có thể làm gì để định hình cho tương lai?
Xuất thân là một doanh nhân và là giáo sư dạy ở trường kinh tế, vì vậy, Scott Galloway nhìn mọi thứ qua lăng kính kinh doanh. Nội dung cốt lõi của Thời kỳ hậu Corona: Luôn có cơ hội trong khủng hoảng sẽ nói về cách thức cơn đại dịch định hình lại môi trường kinh doanh. Việc kinh doanh phải được xem xét trong một bối cảnh cụ thể, vì vậy tác giả sẽ kết nối câu chuyện kinh doanh với câu chuyện xã hội rộng lớn hơn của chúng ta. Scott Galloway cũng dành hẳn một chương cho giáo dục đại học, vì ông tin rằng nền giáo dục đang trong cao trào chuyển đổi mạnh mẽ về bản chất.
Đại dịch, chiến tranh, suy thoái – những cú sốc này thật đau đớn, nhưng theo sau đó thường là những thời điểm thăng hoa nhất trong lịch sử nhân loại. Những thế hệ chịu đựng và chứng kiến nỗi đau là những thế hệ được chuẩn bị tốt nhất để nghênh chiến.
Thế hệ đang lên sẽ mang vác gánh nặng của thế giới hậu Corona như thế nào? Chúng ta vẫn có lý do để hy vọng.
Có thể chúng ta đang hình thành một thế hệ sẽ nắm lấy siêu năng lực của loài chúng ta: hợp tác. Nếu người Anh, người Nga và người Mỹ đã có thể hợp tác chống lại kẻ thù chung cách đây 80 năm, liệu chúng ta có thể tiếp tục hợp tác để tiêu diệt kẻ thù đang đe dọa tất cả 7,7 tỷ người không?
Liệu thế hệ này sẽ quyết định rằng nếu một nửa dân số quốc gia không thể sống quá 60 ngày mà không có sự hỗ trợ của chính phủ, thì chúng ta phải đầu tư hướng tới tương lai nhiều hơn để tiết kiệm hàng nghìn tỷ đô-la cho các khoản kích thích khẩn cấp trong tương lai?
Toàn bộ lịch sử của chúng ta, cũng như tương lai của chúng ta, là của chúng ta. Sự thịnh vượng chung của chúng ta không chỉ xảy ra mà nó đã được định hình. Chúng ta đã chọn con đường này. Không có xu hướng nào là vĩnh viễn và cũng không có gì là không thể bị làm cho tệ thêm hoặc sửa chữa cho tốt hơn.
Trân trọng giới thiệu đến bạn đọc: Thời kỳ hậu Corona: Luôn có cơ hội trong khủng hoảng!', 256, NULL, 1)
INSERT [dbo].[tblBook] ([Id], [Name], [CategoryId], [Price], [SalePrice], [Author], [PublisherId], [PublishYear], [Picture], [Number], [Description], [Page], [Rating], [Status]) VALUES (11, N'Kỹ năng giao tiếp', 2, 10000, 0, N'ĐOÀN CHÍ THIỆN', 2, 2016, N'/storage/emulated/0/Pictures/ImageBook/bd0c2fed3a7417052f581de0f2ef1f6d.jpeg', 200, N'Kỹ năng giao tiếp là một trong những kỹ năng mềm cực kỳ quan trọng trong thế kỷ 21. Đó là một tập hợp những qui tắc, nghệ thuật, cách ứng xử , đối đáp được đúc rút qua kinh nghiệm thực tế hằng ngày giúp mọi người giao tiếp hiệu quả thuyết phục hơn khi áp dụng thuần thục kỹ năng giao tiếp. Trong những năm gần đây, theo đánh giá của một số doanh nghiệp khi tuyển dụng sinh viên tốt nghiệp ra trường thường có ý kiến cho rằng: Hạn chế lớn nhất của hầu hết sinh viên chủ yếu trên các mặt thuộc về kỹ năng mềm, mà đặc biệt là kỹ năng giao tiếp. Tuy nhiên, vấn đề đặt ra lựa chọn kỹ năng giao tiếp như thế nào cho phù hợp với sinh viên bậc giáo dục cao đẳng và đại học để đảm bảo phù hợp với mục tiêu giáo dục đào tạo, nội dung chương trình cũng như phương thức, phương pháp kiểm tra, đánh giá và đặc biệt là nhu cầu thực tiễn. Nhằm giúp mọi đối tượng nói chung và sinh viên nói riêng nâng cao kỹ năng giao tiếp, Thạc sỹ Đoàn Chí Thiện và Thạc sỹ Nguyễn Thị Anh Đào – Đại học Đà Nẵng biên soạn cuốn sách “Kỹ năng giao tiếp”. Nội dung cuốn sách gồm 5 chương, cụ thể như sau: Chương 1. Khái niệm về kỹ năng giao tiếp Chương 2. Các nguyên tắc khi giao tiếp Chương 3. Các loại hình giao tiếp Chương 4. Tính cách, kỹ năng cần thiết và tư thế trong giao tiếp Chương 5. Mối quan hệ, ứng xử và các kiểu trong giao tiếp Hy vọng rằng, với những hướng dẫn cụ thể mang tính hệ thống hóa sẽ góp phần tạo nên diện mạo mới cho hoạt động kỹ năng giao tiếp, một lĩnh vực còn khá mới mẻ trong giáo dục Việt Nam hiện nay. Việc trang bị các kiến thức kỹ năng giao tiếp sẽ giúp sinh viên có nhiều cơ hội việc làm cũng như phát triển sự nghiệp tốt hơn trong tương lai.', 400, NULL, 1)
go

SET IDENTITY_INSERT tblBook off
go

Insert Into tblCart(UserId, BookId, BookCount) Values (2, 1, 2), (2, 2, 1)
go

Insert Into tblPayment(Name) Values (N'Thanh toán khi nhận hàng'), (N'Chuyển khoản ngân hàng')
go 

select * from tblOrder