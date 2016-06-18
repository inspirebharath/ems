/**
 * 
 */
package com.ems.user.dao.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.ems.user.beans.PaginationBean;
import com.ems.user.beans.UserDTO;
import com.ems.user.constants.Constants;
import com.ems.user.dao.UserDao;
import com.ems.user.dao.model.QUserInfo;
import com.ems.user.dao.model.UserInfo;
import com.ems.user.dao.repository.UserRepository;
import com.ems.user.util.CommonUtil;
import com.ems.user.util.StringUtil;
import com.mysema.query.Tuple;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.OrderSpecifier;
import com.mysema.query.types.expr.BooleanExpression;

/**
 * @author Bharath Arya
 *
 */
@Repository("userDao")
public class UserDaoImpl implements UserDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public boolean checkDataExistance(String userName, String emailId, String mobileNo) throws DataAccessException {
		UserInfo userInfo = userRepository.findByUserNameOrEmailIdOrMobileNoAllIgnoreCase(userName, emailId, mobileNo);
		if(!CommonUtil.isNull(userInfo)) {
			return true;
		}
		
		return false;
	}

	@Override
	public UserInfo createUser(UserInfo userInfo) throws DataAccessException {
		return userRepository.save(userInfo);
	}

	@Override
	public UserInfo isValidUser(String userName, String emailId, String password) throws DataAccessException {
		return userRepository.findByUserNameOrEmailIdAndPassword(userName, emailId, password);
	}

	@Override
	public UserInfo getSpecificUser(Long userId) throws DataAccessException {
		return userRepository.findOne(isIdEq(userId));
	}

	@Override
	public UserInfo updateUserInfo(UserInfo userInfo) throws DataAccessException {
		return userRepository.save(userInfo);
	}

	@Override
	public int updateLoginDateTime(Long id, Timestamp loginTime) {
		return userRepository.updateLoginDateTime(id, loginTime);
	}
	
	@Override
	public int updatePassword(Long id, String password, String updatedBy, Timestamp updatedDate) {
		return userRepository.updatePassword(id, password, updatedBy, updatedDate);
	}

	@Override
	public UserInfo findUserByEmailId(String emailId) throws DataAccessException {
		return userRepository.findByEmailIdAllIgnoreCase(emailId);
	}

	@Override
	public UserDTO searchUsers(UserDTO userDto) throws DataAccessException {
		Integer offset = 0;
		Integer limit = Constants.DEFAULT_PAGE_SIZE;
		
		Integer totalRecords = 0;
		
		Integer pageIndex = 1;
		Integer pageSize = Constants.DEFAULT_PAGE_SIZE;
		
		PaginationBean paginationBean = new PaginationBean();
		
		if(null != userDto.getPaginationBean()) {
			paginationBean = userDto.getPaginationBean();
			pageIndex = paginationBean.getPageIndex();
			pageSize = paginationBean.getPageSize();
			totalRecords = paginationBean.getNoOfRecords();
		} else {
			totalRecords = getTotalNumberOfRecords(userDto);
			paginationBean.setNoOfRecords(totalRecords);
			userDto.setPaginationBean(paginationBean);
		}
		
		if (pageIndex != null && pageSize != null) {
			offset = (pageIndex - 1) * pageSize;
			limit = pageSize;
		}
		
		if(null != userDto.getReportPropertiesBean() && userDto.getReportPropertiesBean().isToFetchAllRecords()) {
			offset = 0;
			limit = totalRecords;
		}
		
		boolean canDoWildCardSearch = false;
		
		boolean toOrderByAsc = false;
		
		if(!CommonUtil.isNull(userDto.getReportPropertiesBean())) {
			if(userDto.getReportPropertiesBean().isToDoWildCardSearch()) {
				canDoWildCardSearch = true;
			} else if(userDto.getReportPropertiesBean().isToOrderByAsc()) {
				toOrderByAsc = true;
			}
		}

		List<UserDTO> userInfoList = new ArrayList<>();
		
		JPAQuery query = new JPAQuery(entityManager);
		
		QUserInfo userInfo = QUserInfo.userInfo;
		
		List<Tuple> dataList = query
				.from(userInfo)
				.where(isUserNameEqOrLike(userDto.getUserName(), canDoWildCardSearch),
						isEmailIdEqOrLike(userDto.getEmailId(), canDoWildCardSearch),
						isFirstNameEqOrLike(userDto.getFirstName(), canDoWildCardSearch),
						isLastNameEqOrLike(userDto.getLastName(), canDoWildCardSearch),
						isMobileNoEqOrLike(userDto.getMobileNo(), canDoWildCardSearch),
						isCountryEq(userDto.getCountry()),
						isStateEq(userDto.getState()),
						isCitysEq(userDto.getCity()),
						isUserTypeEq(userDto.getIsGuestUser()),
						isStatusEq(userDto.getStatus()))
				.offset(offset)
				.limit(limit)
				.orderBy(orderById(toOrderByAsc))
				.list(userInfo.id,
					  userInfo.userName,
					  userInfo.firstName,
					  userInfo.lastName,
					  userInfo.emailId,
					  userInfo.mobileNo,
					  userInfo.country,
					  userInfo.state,
					  userInfo.city,
					  userInfo.isGuestUser,
					  userInfo.status,
					  userInfo.lastLoginDateTime);
				
		UserDTO resUserDTO = null;
		
		for (Tuple tuple : dataList) {
			
			resUserDTO = new UserDTO();
			
			resUserDTO.setId(tuple.get(userInfo.id));
			resUserDTO.setUserName(tuple.get(userInfo.userName));
			resUserDTO.setFirstName(tuple.get(userInfo.firstName));
			resUserDTO.setLastName(tuple.get(userInfo.lastName));
			resUserDTO.setEmailId(tuple.get(userInfo.emailId));
			resUserDTO.setMobileNo(tuple.get(userInfo.mobileNo));
			resUserDTO.setCountry(tuple.get(userInfo.country));
			resUserDTO.setState(tuple.get(userInfo.state));
			resUserDTO.setCity(tuple.get(userInfo.city));
			resUserDTO.setIsGuestUser(tuple.get(userInfo.isGuestUser));
			resUserDTO.setStatus(tuple.get(userInfo.status));
			resUserDTO.setLastLoginDateTime(tuple.get(userInfo.lastLoginDateTime));
			
			userInfoList.add(resUserDTO);
		}
		
		userDto.setUserInfoList(userInfoList);
		
		return userDto;
	}
	
	private int getTotalNumberOfRecords(UserDTO userDto) {
		
		boolean canDoWildCardSearch = false;
		
		boolean toOrderByAsc = false;
		
		if(!CommonUtil.isNull(userDto.getReportPropertiesBean())) {
			if(userDto.getReportPropertiesBean().isToDoWildCardSearch()) {
				canDoWildCardSearch = true;
			} else if(userDto.getReportPropertiesBean().isToOrderByAsc()) {
				toOrderByAsc = true;
			}
		}
		
		JPAQuery query = new JPAQuery(entityManager);
		
		QUserInfo userInfo = QUserInfo.userInfo;
		
		List<Long> usersList = query
				.from(userInfo)
				.where(isUserNameEqOrLike(userDto.getUserName(), canDoWildCardSearch),
						isEmailIdEqOrLike(userDto.getEmailId(), canDoWildCardSearch),
						isFirstNameEqOrLike(userDto.getFirstName(), canDoWildCardSearch),
						isLastNameEqOrLike(userDto.getLastName(), canDoWildCardSearch),
						isMobileNoEqOrLike(userDto.getMobileNo(), canDoWildCardSearch),
						isCountryEq(userDto.getCountry()),
						isStateEq(userDto.getState()),
						isCitysEq(userDto.getCity()),
						isUserTypeEq(userDto.getIsGuestUser()),
						isStatusEq(userDto.getStatus()))
				.orderBy(orderById(toOrderByAsc))
				.list(userInfo.id);

		return (usersList != null && !usersList.isEmpty() ? usersList.size() : 0);
		
	}
	
	private BooleanExpression isUserNameEqOrLike(String userName, boolean doWildCardSearch) {
		if(doWildCardSearch) {
			return (!StringUtil.isNullAndEmpty(userName)) ? QUserInfo.userInfo.userName.toUpperCase().like("%" + userName.toUpperCase().replace("*", "") + "%") : null;
		} else {
			return (!StringUtil.isNullAndEmpty(userName)) ? QUserInfo.userInfo.userName.eq(userName) : null;
		}
	}
	
	private BooleanExpression isEmailIdEqOrLike(String emailId, boolean doWildCardSearch) {
		if(doWildCardSearch) {
			return (!StringUtil.isNullAndEmpty(emailId)) ? QUserInfo.userInfo.emailId.toUpperCase().like("%" + emailId.toUpperCase().replace("*", "") + "%") : null;
		} else {
			return (!StringUtil.isNullAndEmpty(emailId)) ? QUserInfo.userInfo.emailId.eq(emailId) : null;
		}
	}
	
	private BooleanExpression isFirstNameEqOrLike(String firstName, boolean doWildCardSearch) {
		if(doWildCardSearch) {
			return (!StringUtil.isNullAndEmpty(firstName)) ? QUserInfo.userInfo.firstName.toUpperCase().like("%" + firstName.toUpperCase().replace("*", "") + "%") : null;
		} else {
			return (!StringUtil.isNullAndEmpty(firstName)) ? QUserInfo.userInfo.firstName.eq(firstName) : null;
		}
	}
	
	private BooleanExpression isLastNameEqOrLike(String lastName, boolean doWildCardSearch) {
		if(doWildCardSearch) {
			return (!StringUtil.isNullAndEmpty(lastName)) ? QUserInfo.userInfo.lastName.toUpperCase().like("%" + lastName.toUpperCase().replace("*", "") + "%") : null;
		} else {
			return (!StringUtil.isNullAndEmpty(lastName)) ? QUserInfo.userInfo.lastName.eq(lastName) : null;
		}
	}

	private BooleanExpression isMobileNoEqOrLike(String mobileNo, boolean doWildCardSearch) {
		if(doWildCardSearch) {
			return (!StringUtil.isNullAndEmpty(mobileNo)) ? QUserInfo.userInfo.mobileNo.toUpperCase().like("%" + mobileNo.toUpperCase().replace("*", "") + "%") : null;
		} else {
			return (!StringUtil.isNullAndEmpty(mobileNo)) ? QUserInfo.userInfo.mobileNo.eq(mobileNo) : null;
		}
	}
	
	private BooleanExpression isStatusEq(String status) {
		return (!StringUtil.isNullAndEmpty(status)) ? QUserInfo.userInfo.status.eq(status) : null;
	}
	
	private BooleanExpression isCountryEq(String country) {
		return (!StringUtil.isNullAndEmpty(country)) ? QUserInfo.userInfo.country.eq(country) : null;
	}
	
	private BooleanExpression isCitysEq(String city) {
		return (!StringUtil.isNullAndEmpty(city)) ? QUserInfo.userInfo.city.eq(city) : null;
	}

	private BooleanExpression isStateEq(String state) {
		return (!StringUtil.isNullAndEmpty(state)) ? QUserInfo.userInfo.state.eq(state) : null;
	}
	
	private BooleanExpression isUserTypeEq(String userType) {
		return (!StringUtil.isNullAndEmpty(userType)) ? QUserInfo.userInfo.isGuestUser.eq(userType) : null;
	}

	private OrderSpecifier<Long> orderById(boolean isToOrderByAsc) {
		if(isToOrderByAsc) {
			return QUserInfo.userInfo.id.asc();
		} else {
			return QUserInfo.userInfo.id.desc();
		}
	}
	
	private BooleanExpression isIdEq(Long adminId) {
		return (!CommonUtil.isNull(adminId)) ? QUserInfo.userInfo.id.eq(adminId) : null;
	}

}