package com.dsd.lottery.db;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dsd.lottery.util.log.LogUtil;

/**
 * MyBatis数据库操作公共类
 * 
 * @author acer-pc
 *
 */
@Transactional
@Repository("myBatisDAO")
public class MyBatisDAO extends SqlSessionDaoSupport {

	private static final Logger logger = LoggerFactory
			.getLogger(MyBatisDAO.class);

	@Autowired
	private SqlSessionFactory sessionFactory;
	
	private static final int DEFAULT_MAX = 10000;
	
	/**避免报错Property 'sqlSessionFactory' or 'sqlSessionTemplate' are required**/
	@Autowired
	public void setSqlSessionFactory(SqlSessionFactory sessionFactory) {
		super.setSqlSessionFactory(sessionFactory);
	}

	/**
	 * 错误代码
	 */
	private final int errorCode = -1;

	/**
	 * 根据参数获取多条数据
	 * 
	 * @param key
	 * @param params
	 * @return
	 */
	public <T> List<T> selectList(String key, Object params) {
		if (StringUtils.isEmpty(key)) {
			logger.warn("key is empty!");
			return null;
		}
		return this.getSqlSession().selectList(key, params);
	}

	/**
	 * 无参数获取多条数据
	 * 
	 * @param key
	 * @return
	 */
	public <T> List<T> selectList(String key) {
		if (StringUtils.isEmpty(key)) {
			logger.warn("key is empty!");
			return null;
		}
		return this.getSqlSession().selectList(key);
	}

	/**
	 * 根据参数获取对象
	 * 
	 * @param key
	 * @param params
	 * @return
	 */
	public <T> T select(String key, Object params) {
		if (StringUtils.isEmpty(key)) {
			logger.warn("key is empty!");
			return null;
		}
		return this.getSqlSession().selectOne(key, params);
	}
	
	/**
	 * 无参数获取对象
	 * 
	 * @param key
	 * @param params
	 * @return
	 */
	public <T> T select(String key) {
		if (StringUtils.isEmpty(key)) {
			logger.warn("key is empty!");
			return null;
		}
		return this.getSqlSession().selectOne(key);
	}

	/**
	 * 无参数获取Map
	 * 
	 * @param key
	 * @param params
	 * @return
	 */
	public <K, V> Map<K, V> selectMap(String key, String mapKey) {
		if (StringUtils.isEmpty(key)) {
			logger.warn("key is empty!");
			return null;
		}
		return this.getSqlSession().selectMap(key, mapKey);
	}

	/**
	 * 根据参数获取Map
	 * 
	 * @param key
	 * @param params
	 * @return
	 */
	public <K, V> Map<K, V> selectMap(String key, String mapKey, Object params) {
		if (StringUtils.isEmpty(key)) {
			logger.warn("key is empty!");
			return null;
		}
		return this.getSqlSession().selectMap(key, params, mapKey);
	}


	/**
	 * 更新
	 * 
	 * @param key
	 * @param params
	 * @return
	 */
	public boolean update(String key) {
		return update(key, null);
	}

	/**
	 * 更新
	 * 
	 * @param key
	 * @param params
	 * @return
	 */
	public boolean update(String key, Object params) {
		if (StringUtils.isEmpty(key)) {
			logger.warn("key is empty!");
			return false;
		}
		try {
			this.getSqlSession().update(key, params);
		} catch (Exception e) {
			LogUtil.error("update", "更新数据库错误！"+params.toString(), e);
		}
		
		return true;
	}

	/**
	 * 批量更新（效率没有在配置文件上的高forEach）
	 * 
	 * @param key
	 * @param params
	 * @return
	 */
	public <T> boolean batchUpdate(String key, List<T> params) {
		if (StringUtils.isEmpty(key)) {
			logger.warn("key is empty!");
			return false;
		}

		if (null == params || params.isEmpty()) {
			logger.warn("update'value is empty!");
			return false;
		}
		SqlSession session = sessionFactory.openSession();
		try {
			for (T obj : params) {
				session.update(key, obj);
			}
			session.commit();
			return true;
		} catch (Exception e) {
			session.rollback();
			logger.error(key + " batchUpdate is faild", e);
			return false;
		} finally {
			session.close();
		}
	}

	/**
	 * 批量更新（高效率批量更新forEach）
	 * 
	 * @param key
	 * @param params
	 * @return
	 */
	public <T> boolean batchHightUpdate(String key, List<T> params) {
		if (StringUtils.isEmpty(key)) {
			logger.warn("key is empty!");
			return false;
		}

		if (null == params || params.isEmpty()) {
			logger.warn("update'value is empty!");
			return false;
		}

		SqlSession session = sessionFactory.openSession();
		try {
			session.update(key, params);
			session.commit();
			return true;
		} catch (Exception e) {
			session.rollback();
			logger.error(key + " batchUpdate is faild", e);
			return false;
		} finally {
			session.close();
		}
	}

	/**
	 * 无参数添加
	 * 
	 * @param key
	 * @param params
	 * @return
	 */
	public boolean insert(String key) {
		if (StringUtils.isEmpty(key)) {
			logger.warn("key is empty!");
			return false;
		}
		this.getSqlSession().insert(key);
		return true;
	}

	/**
	 * 添加
	 * 
	 * @param key
	 * @param params
	 * @return
	 */
	public boolean insert(String key, Object params) {
		if (StringUtils.isEmpty(key)) {
			logger.warn("key is empty!");
			return false;
		}
		try {
			this.getSqlSession().insert(key, params);
		} catch (Exception e) {
			LogUtil.error("insert", "插入数据库错误！"+params.toString(), e);
		}
		return true;
	}

	/**
	 * 批量插入（效率没有在配置文件上的高forEach）
	 * 
	 * @param key
	 * @param params
	 * @return
	 */
	public <T> boolean batchInsert(String key, List<T> params) {
		if (StringUtils.isEmpty(key)) {
			logger.warn("key is empty!");
			return false;
		}

		if (null == params || params.isEmpty()) {
			logger.warn("add'value is empty!");
			return false;
		}
		SqlSession session = sessionFactory.openSession();
		try {
			for (T obj : params) {
				session.insert(key, obj);
			}
			session.commit();
			return true;
		} catch (Exception e) {
			session.rollback();
			logger.error(key + " batchInsert is faild", e);
			return false;
		} finally {
			session.close();
		}
	}

	/**
	 * 批量插入（高效率批量更新配置加forEach）,每
	 * 
	 * @param key
	 * @param params
	 * @return
	 */
	public <T> boolean batchHightInsert(String key, List<T> params, final int max) {
		if (StringUtils.isEmpty(key)) {
			logger.warn("key is empty!");
			return false;
		}

		if (null == params || params.isEmpty()) {
			logger.warn("add'value is empty!");
			return false;
		}
		SqlSession session = sessionFactory.openSession();
		try {
			int size = params.size();
			if(size > max)
			{
				int start = 0;
				int end = 0;
				while(start <= size)
				{
					end = end + max > size ? size : end+max;
					session.insert(key,params.subList(start, end));
					start += max;
				}
			}
			else
			{
				session.insert(key, params);
			}
			session.commit();
			return true;
		} catch (Exception e) {
			session.rollback();
			LogUtil.error(key + " batchInsert is faild", e);
			return false;
		} finally {
			session.close();
		}
	}
	
	/**
	 * 批量插入（高效率批量更新配置加forEach）,每
	 * 
	 * @param key
	 * @param params
	 * @return
	 */
	public <T> boolean batchHightInsert(String key, List<T> params) {
		return batchHightInsert(key, params, DEFAULT_MAX);
	}

	public int delete(String key) {
		if (StringUtils.isEmpty(key)) {
			logger.warn("key is empty!");
			return errorCode;
		}
		try {
			return this.getSqlSession().delete(key);
		} catch (Exception e) {
			logger.error("delete",String.format("delete %s is faied", key), e);
			return errorCode;
		}
	}

	public int delete(String key, Object params) {
		if (StringUtils.isEmpty(key)) {
			logger.warn("key is empty!");
			return errorCode;
		}
		try {
			return this.getSqlSession().delete(key, params);
		} catch (Exception e) {
			logger.error("delete",String.format("delete %s is faied", key), e);
			return errorCode;
		}
	}
	
	/**
	 * 直接执行拼接的sql语句
	 * @param sql
	 */
	public boolean excuteSQL(String sql)
	{
		boolean isSuccess =false;
		try {
			this.getSqlSession().insert("dynamicMapper.createDynamicSql",new SQLAdapter(sql));
			isSuccess = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isSuccess;
	}
}
