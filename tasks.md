# Tasks

/products

- post()

  1. return 201

     - return 201 when create product — 5 6
     - return uri when create product — 5 9
     - save and find product in product repository (ProductRepository.createProduct) — 15 12
     - return 201 when create product with specified parameter — 10 3

  2. return 400

     - return 400 when name, description or price is null — 5 4

     ​

- get()

  1. return 200

     - return 200 when get products — 5 2

     - get products in product repository (ProductRepository.getAllProducts) — 10 5

     - return detail when get products — 5 5

       ​

/products/{productId}

- get()

  1. return 200
     - return 200 when get product — 5 3
     - find product in product repository (ProductRepository.findById) — 15 3
     - return detail when get product — 7  3
  2. return 404
     - return 404 when no product exists — 5 7

  ​

/users

- post()

  1. return 201

     - return 201 when create user — 5 5
     - return uri when create user — 7 7
     - save and find user in user repository (UserRepository.createUser) — 15 15
     - return 201 when create user with specified parameter — 10 3

  2. return 400

     - find user by name in user repository (UserRepository.findUserByName) — 10 7

     - return 400 when user exists — 7 5

     - return 400 when name is null — 7 4

       ​

/users/{id}/orders

- post()

  1. return 201

     - return 201 when create order — 5 5
     - return uri when create order — 10 15
     - get product price in  repository(UserRepository.getProductPrice) — 10 5
     - save and find order in user repository (UserRepository.createOrder) — 15 20
     - return 201 when create oder with specified parameter — 10 4

  2. return 400

     - find user by id in user repository — 5 7
     - return 400 when create order with no exist user — 5 12
     - return 400 when name, address, phone, order_items, product_id or quantity is null — 10 5
     - return 400 when product_id is not found — 5 7

     ​

- get()

  1. return 200

     - return 200 when get orders — 5 5
     - get orders in user repository in order repository(UserRepository.getOrders) — 15 9
     - return detail when get orders — 10 10

  2. return 400 when get order with no exist user — 5 3

     ​

/users/{id}/orders/{orderId}

- get()

  1. return 200

     - return 200 when get order — 5 2
     - find order by order_id in user repository (UserRepository.findOrderById) — 10 11
     - return detail when get order — 15 7

  2. return 404 when no order exists — 10 5

     ​

/users/{id}/orders/{orderId}/payments

- post()

  1. return 201

     - return 201 when create payment — 5 3
     - return uri when create payment — 5 8
     - save payment in payment repository (UserRepository.creatPayment) — 15 18
     - return 201 when create payment with specified parameter — 10 6

  2. return 400

     - find whether payment exist in payment repository (UserRepository.findPaymentById)  —10 6
     - return 400 when payment exists — 5 5
     - return 400 when pay_type or amount is null — 5 4

     ​

- get()

  1. return 200
     - return 200 when get payment — 5 4
     - return details when get payment — 10 11
  2. return 404 when no payment exists — 5 4