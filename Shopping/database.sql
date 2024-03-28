USE MASTER
GO
IF EXISTS(SELECT name FROM sys.databases WHERE name = 'PRJ')
  DROP DATABASE PRJ;
GO

CREATE DATABASE PRJ;
GO

-- Use the database
USE PRJ;
GO

-- Create the table for categories
CREATE TABLE categories (
  id INT IDENTITY(1,1) PRIMARY KEY,
  name NVARCHAR(50) NOT NULL
);
GO

-- Insert sample data into the categories table
INSERT INTO categories (name) VALUES
  (N'Jean'),
  (N'Hoodie'),
  (N'VEST'),
  (N'JUMPER');
GO

-- Create the table for products
CREATE TABLE products (
  id INT IDENTITY(1,1) PRIMARY KEY,
  name NVARCHAR(100) NOT NULL,
  price DECIMAL(10, 2) NOT NULL,
  quantity INT,
  image NVARCHAR(500),
  category_id INT,
  description NVARCHAR(500),
  FOREIGN KEY (category_id) REFERENCES categories(id) ON DELETE CASCADE ON UPDATE CASCADE
);
GO


-- Insert sample data into the products table
INSERT INTO products (name, price, quantity, image, category_id, description) VALUES
  (N'Straight Regular Jeans ', 99.99, 10, 'https://lp2.hm.com/hmgoepprod?set=source[/b1/27/b1271fb543b517771c65daee1b7919929885eb98.jpg],origin[dam],category[],type[DESCRIPTIVESTILLLIFE],res[y],hmver[2]&call=url[file:/product/main]', 1, N'Jean Pro'),
  (N'Relaxed Jeans', 49.99, 5, 'https://lp2.hm.com/hmgoepprod?set=source[/e8/7d/e87d130892159f3dfb7d267b64ac02cc72d242ec.jpg],origin[dam],category[],type[DESCRIPTIVESTILLLIFE],res[y],hmver[2]&call=url[file:/product/main]', 1, N'Jean Pro'),
  (N'Skinny Jeans', 149.99, 3, 'https://lp2.hm.com/hmgoepprod?set=source[/12/f2/12f2c49785b8e6aa1ee8123a44de38b3919ed180.jpg],origin[dam],category[],type[DESCRIPTIVESTILLLIFE],res[y],hmver[2]&call=url[file:/product/main]', 1, N'Jean Pro'),
  (N'Bootcut Loose Jeans', 79.99, 8, 'https://lp2.hm.com/hmgoepprod?set=source[/43/c9/43c9862345feb3993374d44dbf2b750669b55f5f.jpg],origin[dam],category[],type[DESCRIPTIVESTILLLIFE],res[y],hmver[2]&call=url[file:/product/main]', 1, N'Jean Pro'),
  (N'Oversized Fit', 39.99, 12, 'https://lp2.hm.com/hmgoepprod?set=source[/dc/2b/dc2b5a7c3f37975eefe03a031a3e637d9d294844.jpg],origin[dam],category[men_hoodiessweatshirts_hoodies],type[DESCRIPTIVESTILLLIFE],res[y],hmver[2]&call=url[file:/product/main]', 2, N'Hoodie Pro'),
  (N'Loose Fit', 199.99, 6, 'https://lp2.hm.com/hmgoepprod?set=source[/82/fd/82fd6561738acd5dabb240afff6600850b2819ad.jpg],origin[dam],category[men_hoodiessweatshirts_sweatshirts],type[DESCRIPTIVESTILLLIFE],res[y],hmver[2]&call=url[file:/product/main]', 2,  N'Hoodie Pro'),
  (N'Slim Fit', 119.99, 15, 'https://lp2.hm.com/hmgoepprod?set=source[/9d/69/9d6939a53f3597ac46de19bf2c7b1445e757e7a0.jpg],origin[dam],category[],type[DESCRIPTIVESTILLLIFE],res[y],hmver[2]&call=url[file:/product/main]', 3, N'VEST pro'),
  (N'Regular Fit', 69.99, 9, 'https://lp2.hm.com/hmgoepprod?set=source[/0e/2a/0e2a6e6ac156c26baf2ceaffdbd345035199fcf7.jpg],origin[dam],category[men_blazerssuits_blazers],type[DESCRIPTIVESTILLLIFE],res[y],hmver[2]&call=url[file:/product/main]', 3, N'VEST pro'),
  (N'jersey Slim Fit', 129.99, 4, 'https://lp2.hm.com/hmgoepprod?set=source[/86/49/86495c859a7d581d6051505a893f5c3950021aa1.jpg],origin[dam],category[],type[DESCRIPTIVESTILLLIFE],res[y],hmver[2]&call=url[file:/product/main]', 4, N'JUMPER pro'),
  (N'polo Regular Fit', 89.99, 0, 'https://lp2.hm.com/hmgoepprod?set=source[/5e/31/5e315bbbac7cb4c0b9de85e5af332aa2ffe1612f.jpg],origin[dam],category[],type[DESCRIPTIVESTILLLIFE],res[y],hmver[2]&call=url[file:/product/main]', 4, N'JUMPER pro');
GO

CREATE TABLE colors (
  id INT IDENTITY(1,1) PRIMARY KEY,
  name NVARCHAR(50) NOT NULL
);
GO

-- Insert sample data into the colors table
INSERT INTO colors (name) VALUES
  (N'Red'),
  (N'Blue'),
  (N'Green');
-- Create the sizes table
CREATE TABLE sizes (
  id INT IDENTITY(1,1) PRIMARY KEY,
  name NVARCHAR(50) NOT NULL
);
GO

-- Insert sample data into the sizes table
INSERT INTO sizes (name) VALUES
  (N'S'),
  (N'M'),
  (N'L'),
  (N'XL');

-- Create the product_variants table
CREATE TABLE product_variants (
  id INT IDENTITY(1,1) PRIMARY KEY,
  product_id INT NOT NULL,
  color_id INT NOT NULL,
  size_id INT NOT NULL,
  FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (color_id) REFERENCES colors(id) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (size_id) REFERENCES sizes(id) ON DELETE CASCADE ON UPDATE CASCADE
);
GO
INSERT INTO product_variants (product_id, size_id,color_id) VALUES
  (1, 1, 1),  
  (1, 2, 2), 
  (1, 3, 2),  
  (1, 4, 3),  
  (2, 2, 1), 
  (2, 4, 3),  
  (2, 1, 3),  
  (2, 3, 1),  
  (3, 1, 2),  
  (3, 3, 3), 
  (3, 2, 1), 
  (3, 4, 2), 
  (4, 3, 3), 
  (4, 1, 2), 
  (4, 2, 3),
  (6, 3, 3), 
  (6, 4, 1), 
  (6, 2, 3), 
  (7, 3, 3),
  (7, 4, 1), 
  (7, 2, 3), 
   (8, 3, 3),
  (8, 1, 2), 
  (8, 2, 3), 
   (9, 3, 3), 
  (9, 4, 1), 
  (9, 1, 2), 
  (9, 2, 3), 
  (10, 3, 3),
  (10, 1, 2), 
  (10, 2, 3), 
  (5, 2, 1),
  (5, 4, 2), 
  (5, 3, 3);

-- Create the table for accounts
CREATE TABLE accounts (
  id INT IDENTITY(1,1) PRIMARY KEY,
  username VARCHAR(50) NOT NULL,
  password VARCHAR(50) NOT NULL,
  fullname NVARCHAR(50) NOT NULL,
  email VARCHAR(100) NOT NULL,
  address NVARCHAR(100) NOT NULL,
  created_at DATETIME DEFAULT GETDATE(),
  Status TINYINT DEFAULT 1, -- 1 is active , 0 is blocked
  role TINYINT DEFAULT 0 -- 0 is user, 1 is admin
);
GO
select * from accounts
-- Insert sample data into the accounts table
INSERT INTO accounts (username, password, fullname, email, address, status, role) VALUES
  ('1', '1', N'Nguyen Van A', 'user1@example.com', 'HN', 1, 1),
  ('user2', '2', N'Nguyen Van B', 'user2@example.com', 'HN', 1, 0),
  ('user3', '3', N'Nguyen Van C', 'user3@example.com', 'HN', 1, 0),
  ('user4', '4', N'Nguyen Van D', 'user4@example.com', 'HN', 1, 0),
  ('user5', '5', N'Nguyen Van E', 'user5@example.com', 'HN', 1, 0),
  ('user6', '6', N'Nguyen Van F', 'user6@example.com', 'HN', 1, 0),
  ('user7', '7', N'Nguyen Van G', 'user7@example.com', 'HN', 1, 0),
  ('user8', '8', N'Nguyen Van H', 'user8@example.com', 'HN', 1, 0),
  ('user9', '9', N'Nguyen Van Y', 'user9@example.com', 'HN', 1, 0),
  ('user10', '10', N'Nguyen Van K', 'user10@example.com', 'HN', 1, 0);
GO

-- Create the table for orders
CREATE TABLE orders (
  id INT IDENTITY(1,1) PRIMARY KEY,
  account_id INT NOT NULL,
  product_id INT NOT NULL,
  size_id INT NOT NULL,
  color_id INT NOT NULL,
  quantity INT NOT NULL,
  price DECIMAL(10, 2) NOT NULL,
  created_at DATETIME DEFAULT GETDATE(),
  status INT DEFAULT 0,
  FOREIGN KEY (account_id) REFERENCES accounts(id) ON DELETE CASCADE,
  FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE,
  FOREIGN KEY (size_id) REFERENCES sizes(id),
  FOREIGN KEY (color_id) REFERENCES colors(id)
);
GO

-- Insert sample data into the orders table
INSERT INTO orders (account_id, product_id, size_id, color_id, quantity, price) VALUES
  (1, 1, 1, 1, 2, 199.98),
  (2, 1, 1, 1, 1, 99.99),
  (3, 2, 2, 2, 3, 149.97),
  (4, 3, 1, 2, 2, 299.98),
  (5, 3, 2, 1, 1, 149.99),
  (6, 2, 1, 2, 2, 99.98),
  (7, 1, 2, 1, 3, 299.97),
  (8, 3, 1, 1, 1, 149.99),
  (9, 2, 2, 2, 2, 99.98),
  (10, 1, 1, 1, 1, 99.99);


 