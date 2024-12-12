package ru.nicetu.predator.u.modules.launch.chart.service;

@Service
class OrderService(
        val orderRepository: OrderRepository,
        ) {

    @Transactional
    fun createOrder(user: User, price: Long, count: Int) : Int =
            orderRepository.save(
    count = count,
    price = price,
    date = LocalDate.now(),
    userId = user.id,
            ).let { orderRepository.getLastInsertedOrderId() }

    fun getOrders(user: User) : List<Order> = orderRepository.getAllByUser(user.id)
}
