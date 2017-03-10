CREATE TABLE WritingGroups(
    GroupName varchar(50) NOT NULL,
    HeadWriter varchar(50),
    YearFormed smallint,
    Subject varchar(50),
    CONSTRAINT writing_pk PRIMARY KEY (GroupName)
);

CREATE TABLE Publishers(
    PublisherName varchar(50) NOT NULL,
    PublisherAddress varchar(50),
    PublisherPhone varchar(50),
    PublisherEmail varchar(50),
    CONSTRAINT publisher_pk PRIMARY KEY (PublisherName)
);

CREATE TABLE Books(
    GroupName varchar(50) NOT NULL,
    BookTitle varchar(50) NOT NULL,
    PublisherName varchar(50) NOT NULL,
    YearPublished Integer,
    NumberOfPages INTEGER,
    CONSTRAINT book_pk PRIMARY KEY (GroupName, BookTitle, PublisherName),
    CONSTRAINT books_fk FOREIGN KEY (GroupName)
    REFERENCES WritingGroups (GroupName),
    CONSTRAINT book_fk FOREIGN KEY (PublisherName)
    REFERENCES Publishers (PublisherName)
    
);