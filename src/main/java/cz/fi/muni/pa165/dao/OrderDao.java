package cz.fi.muni.pa165.dao;

import java.util.Date;
import java.util.List;

import cz.fi.muni.pa165.dto.OrderDTO;
import cz.fi.muni.pa165.entity.Order;
import cz.fi.muni.pa165.entity.OrderState;
import cz.fi.muni.pa165.entity.User;

public interface OrderDao {
	public void create(Order order);
	public void update(Order order);
	public List<Order> findAll();
	public List<Order> findByUser(User u);
	public Order findById(Long id);
	public void removeById(Long id)  throws IllegalArgumentException;
	public List<Order> findAllWithState(OrderState state);
	List<Order> getOrdersCreatedBetween(Date start, Date end);
}
