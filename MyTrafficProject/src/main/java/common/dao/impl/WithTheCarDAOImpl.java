package common.dao.impl;

import common.constant.Constants;
import common.dao.IWithTheCarDAO;
import common.jdbc.JDBCHelper;
import common.utils.DateUtils;

public class WithTheCarDAOImpl implements IWithTheCarDAO {

	@Override
	public void updateTestData(String cars) {
		JDBCHelper jdbcHelper = JDBCHelper.getInstance();
		String sql = "UPDATE task set task_param = ? WHERE task_id = 3";
		Object[] params = new Object[]{"{\"startDate\":[\""+DateUtils.getTodayDate()+"\"],\"endDate\":[\""+DateUtils.getTodayDate()+"\"],\""+Constants.FIELD_CARS+"\":[\""+cars+"\"]}"};
		jdbcHelper.executeUpdate(sql, params);
	}

}
