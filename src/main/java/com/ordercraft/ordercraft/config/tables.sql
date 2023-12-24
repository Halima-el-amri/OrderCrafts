
CREATE TABLE IF NOT EXISTS Users (
                                     userId INT PRIMARY KEY AUTO_INCREMENT,
                                     username VARCHAR(255) NOT NULL,
                                     password VARCHAR(255) NOT NULL
);


CREATE TABLE IF NOT EXISTS Clients (
                                       clientId INT PRIMARY KEY AUTO_INCREMENT,
                                       clientName VARCHAR(255) NOT NULL,
                                       contactInfo VARCHAR(255) NOT NULL
);

-- Table for Items
CREATE TABLE IF NOT EXISTS Products (
                                        itemId INT PRIMARY KEY AUTO_INCREMENT,
                                        itemName VARCHAR(255) NOT NULL,
                                        price DOUBLE NOT NULL,
                                        quantity INT NOT NULL
);

-- Table for Orders
CREATE TABLE IF NOT EXISTS Orders (
                                      orderId INT PRIMARY KEY AUTO_INCREMENT,
                                      clientId INT,
                                      orderDate DATE,
                                      isCompleted BOOLEAN,
                                      FOREIGN KEY (clientId) REFERENCES Clients(clientId)
);
