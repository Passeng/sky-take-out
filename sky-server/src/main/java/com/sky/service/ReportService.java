package com.sky.service;

import com.sky.vo.OrderReportVO;
import com.sky.vo.SalesTop10ReportVO;
import com.sky.vo.TurnoverReportVO;
import com.sky.vo.UserReportVO;

import java.time.LocalDate;

public interface ReportService {
	/**
	 * 通过指定时间内的统计营业额数据
	 *
	 * @param begin
	 * @param end
	 * @return
	 */
	TurnoverReportVO getTurnoverStatistics(LocalDate begin, LocalDate end);

	/**
	 * 通过指定时间内的统计用户数据
	 *
	 * @param begin
	 * @param end
	 * @return
	 */
	UserReportVO getUserStatistics(LocalDate begin, LocalDate end);

	/**
	 * 通过指定时间内的统计订单数据
	 *
	 * @param begin
	 * @param end
	 * @return
	 */
	OrderReportVO getOrderStatistics(LocalDate begin, LocalDate end);

	/**
	 * 通过指定时间内的统计销量排名数据
	 *
	 * @param begin
	 * @param end
	 * @return
	 */
	SalesTop10ReportVO getSalesTop10(LocalDate begin, LocalDate end);
}
