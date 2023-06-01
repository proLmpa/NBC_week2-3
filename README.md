# NBC_week2-3

# version.1
* Essential points
 - Main -> Kiosk & Home : for adopting OOP, eliminating static statement
 - Menu, Product, Order: encapsulated

# version.2
* modified points
 - Order class: 'productCount' field(data-type: map) added for 'counting products'
 - Product class: 'option' field(data-type: float) added for 'notifying & selecting options'
 - Sold class: newly created for storing sold product's price and saled count

# version.2_1
* modified points
 - Kiosk, Menu, Order class: comments added

# version.2_2
* modified points
 - Kiosk : 'productMap' field's datatype became Map<Menu, ArrayList<Product>>
 - Product: use 'super()' to inherit Menu's fields & methods
