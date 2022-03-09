UP -> docker-compose up --build
DOWN -> docker-compose down --rmi local


DB:
I have create dummy data for testing
4 Customer with id: [1, 2], their carts with id: [1, 2], addresses with id: [1, 2]
4 products
4 listings

There are 2 services as mentioned in docker-compose file
URLs can be acceessed via /swagger-ui.html

1. API service http://localhost:3000
to create order first create line items by url http://localhost:3000/api/v1/cart/1/product/1/add/2
add items to a cart of a customer using this url and then hit /api/v1/carts/{cartId}/orders process-orders
with multiple payments
	- user id should be same as that of cart
	- can split the amount in multiple payments
	- order status is not true until paid >= total
	- order status can be check via http://localhost:3001/api/v1/orders


2.order processing service http://localhost:3001
it is a backend service, still i have exposed few GET url for it, for testing purpose

KAFKA
All the kafka related code starts  with comment KAFKA CONFIG

1. bulk orders
	to do this we first need to create line items of cart
	you can simulate this by hitting url /api/v1/cart/create-carts via API service
	next is to send multiple orders to kafka
	hit url: /api/v1/orders/bulk/create via API service
	this will add new orders to kafka and will be processed by the order processing service.
	*NOTE*: above steps must be taken in the order mentioned, orders can`t be place without carts having some products

2. cancel orders
	/api/v1/orders/bulk/cancel
	all the orders will be cancelled
	API service adds them to kafka queue and they get processed by order processing service.
	