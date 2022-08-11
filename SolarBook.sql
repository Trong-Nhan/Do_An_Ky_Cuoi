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

Create Table tblPromoCode(
	Id int primary key identity,
	[Name] varchar(20),
	[Description] nvarchar(255),
	[Type] bit,
	[Value] int
)
GO

Create Table tblOrder(
	Id int primary key identity,
	UserId int,
	OrderDate datetime,
	PromoCodeId int,
	TotalPrice float,
	CityId int,
	ShippingAddress text,
	ShippingPrice int,
	PaymentId int,
	Note nvarchar(255),
	CreatedDate Date

	Foreign Key (UserId) References tblUser(Id),
	Foreign Key (PromoCodeId) References tblPromoCode(Id),
	Foreign Key (CityId) References tblCity(Id),
	Foreign Key (PaymentId) References tblPayment(Id)
)
GO

Create Table tblOrderDetail(
	OrderId int,
	BookId int,
	Number int,

	Primary Key (OrderId, BookId),
	Foreign Key (OrderId) References tblOrder(Id),
	Foreign Key (BookId) References tblBook(Id)
)
GO

Create Table tblNews(
	Id int primary key identity,
	[Name] nvarchar(255),
	[Description] nvarchar(255),
	Detail text,
	[Picture] nvarchar(255),
	CreatedDate Date
)

GO

Create Table tblRating(
	Id int primary key identity,
	UserId int,
	BookId int, 
	Rating int,
	Comment text,
	CreatedDate Date

	Foreign Key (UserId) References tblUser(Id),
	Foreign Key (BookId) References tblBook(Id)
)

GO

Select * From tblUser

Insert Into tblCity(Name) Values (N'Hà Nội'), (N'TP. Hồ Chí Minh'), (N'Đà Nẵng');

Insert Into tblUser(Name, Phone, Password, Email, CityId, Role) Values ('Admin', '0974079806', '123456', 'tuanng16apu@gmail.com', 1, 'admin')
Insert Into tblUser(Name, Phone, Password, Email, CityId, Role) Values (N'Nguyễn Tuấn Sơn', '0974079806', 'Ts311297', 'tson.nguyen3112@gmail.com', 1, 'user')


Insert Into tblNews(Name, Description, Detail, Picture, CreatedDate) Values (N'Tin tuc', N'Mo ta', N'Chi tiet', 'news1.png','2006/12/12')
Insert Into tblNews(Name, Description, Detail, Picture, CreatedDate) Values (N'Tin tuc', N'Mo ta', N'Chi tiet', 'news1.png','2006/12/12')
Insert Into tblNews(Name, Description, Detail, Picture, CreatedDate) Values (N'Tin tuc', N'Mo ta', N'Chi tiet', 'news1.png','2006/12/12')
Insert Into tblNews(Name, Description, Detail, Picture, CreatedDate) Values (N'Tin tuc', N'Mo ta', N'Chi tiet', 'news1.png','2006/12/12')
Insert Into tblNews(Name, Description, Detail, Picture, CreatedDate) Values (N'Tin tuc', N'Mo ta', N'Chi tiet', 'news1.png','2006/12/12')
go

Insert Into tblCategory(Name) Values (N'Sách thiếu nhi'), (N'Sách dành cho giới trẻ'), (N'Sách Chính trị - Xã hội'), (N'Tủ sách gia đình'), (N'Sách Giáo khoa - Giáo trình'), (N'Sách ngoại ngữ'), 
(N'Sách Quản lý - Kinh tế'), (N'Sách Khoa học - Công nghệ'), (N'Sách Văn học - Nghệ thuật'), (N'Sách khác')
GO
Insert Into tblPublisher(Name) Values (N'Trẻ'), (N'Thanh Hóa'), (N'Lao Động - Xã Hội'), (N'Hội Nhà Văn')
GO


Insert Into tblBook(Name, CategoryId, Price, SalePrice, Author, PublisherId, PublishYear, Picture, Number, Description, Page, Status) Values
(N'Harry Potter và Bảo Bối Tử Thần', 9, 210000, 0, 'J.K.Rowling', 1, 2021, '/storage/emulated/0/Pictures/book1.jpg', 11, N'Harry Potter đang chuẩn bị rời khoiar nhà Dursley', 846, 1),
(N'Cuộc sống của bạn đã tốt đẹp chưa', 2, 63000, 0, 'Marcia Ullett', 2, 2016, '/storage/emulated/0/Pictures/book2.jpg', 7, N'Cuộc sống của bạn đã tốt đẹp chưa? ', 198, 1),
(N'11 bí quyết giao tiếp để thành công', 2, 109000, 0, 'M.T.Lederman', 3, 2019, '/storage/emulated/0/Pictures/book3.jpg', 5, N'Trái với những gì nhiều chuyên gia về networking tư vấn', 284, 1),
(N'Tiếng gọi nơi hoang dã', 9, 72000, 0, 'Jack London', 4, 2018, '/storage/emulated/0/Pictures/book4.jpg', 9, N'Tiếng gọi nơi hoang dã là cuốn sashc hay', 184, 1)
GO
Select * From tblCategory
Select * From tblBook