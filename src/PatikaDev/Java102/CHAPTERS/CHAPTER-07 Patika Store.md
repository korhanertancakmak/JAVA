# Patika Store

The trail team decided to open a virtual store where electronic goods are sold,
and they want you, the trail volunteers, to provide the product management system of this store.

* The name of the Virtual Store will be "JAVA.PatikaStore.PatikaStore."  
* Brands will be created in the store and the products will match these brands.  
* ***id***: The unique number of the brand registered in the system  
* ***name***: Name of the brand  
* When listing brands, they should always be listed in alphabetical order.  
* Brands should be added statically within code blocks in the following order.  
* ***Brands***: Samsung, Lenovo, Apple, Huawei, Casper, Asus, HP, Xiaomi, Monster  
* It is currently planned to sell two types of product groups in the store: Mobile Phones, Notebooks.  
* Different product groups should be able to be added later.  

* Features of Mobile Phone products:  

  * The unique number of the products registered in the system
  * Unit price
  * Discount rate
  * The amount of stock
  * Name of the product
  * Brand information (Brands added to the system will be used)
  * Phone memory information (128 GB, 64 GB)
  * Screen Size (6.1 Inc)
  * Battery Power (4000)
  * RAM (6MB)
  * Color (Black, Red, Blue)  

* Features of notebook products:  
  * The unique number of the products registered in the system
  * Unit price
  * Discount rate
  * The amount of stock
  * Name of the product
  * Brand information (Brands added to the system will be used)
  * RAM (8GB)
  * Storage (512 SSDs)
  * Screen Size (14 inches)

* The user should be able to list the products in the relevant category (Notebooks, Mobile Phones, etc.) through 
the system.
* When listing the products, they should be displayed on the console screen in table form (System.out.format() can 
be used).
* The user should be able to add a product and select the group of the products (Mobile Phone, JAVA.PatikaStore.Notebook, etc.).
* The user should be able to delete products based on their unique number.
* The user should be able to filter and list products based on their unique numbers and brands.

Brand Listing Example :  

```html  
JAVA.PatikaStore.PatikaStore JAVA.PatikaStore.Product Management Panel!
1 - JAVA.PatikaStore.Notebook
2 - Mobile Phone
3 - List Brand
0 - Sign Out
Your choice: 3
Our brands
--------------
- Apple
- Asus
- Casper
- HP
- Huawei
- Lenovo
- Monster
- Samsung
- Xiaomi
```

JAVA.PatikaStore.Product Listing Example :

```java  
import JAVA.PatikaStore.Product;

Notebook List

----------------------------------------------------------------------------------------------------
        |ID |
Product Name                  |Price     |Brand     |Storage   |Screen    |RAM         |
        ----------------------------------------------------------------------------------------------------
        |1|
HUAWEI Matebook 14|7000.0$  |Huawei    |512|14.0|16|
        |2|
LENOVO V14
IGL                |3699.0$  |Lenovo    |1024|14.0|8|
        |3|
ASUS Tuf
Gaming               |8199.0$  |Asus      |2048|15.6|32|
        ----------------------------------------------------------------------------------------------------

Mobile Phone
List

--------------------------------------------------------------------------------------------------------------------------------------
        |ID |
Product Name                  |Price     |Brand     |Storage   |Screen    |Camera    |Battery   |RAM       |Color     |
        --------------------------------------------------------------------------------------------------------------------------------------
        |1|
SAMSUNG GALAXY
A51            |3199.0$  |Samsung   |128|6.5|32|4000.0|6|Black     |
        |2|iPhone 11 64GB               |7379.0$  |Apple     |64|6.1|5|3046.0|6|Blue      |
        |3|
Redmi Note 10Pro 8GB         |4012.0$  |Xiaomi    |128|6.5|35|4000.0|12|White     |
        --------------------------------------------------------------------------------------------------------------------------------------
```


```java  

```
