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
import com.ems.user.beans.PartnerDTO;
import com.ems.user.constants.Constants;
import com.ems.user.dao.PartnerDao;
import com.ems.user.dao.model.PartnerInfo;
import com.ems.user.dao.model.QPartnerInfo;
import com.ems.user.dao.repository.PartnerRepository;
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
@Repository("partnerDao")
public class PartnerDaoImpl implements PartnerDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private PartnerRepository partnerRepository;

	@Override
	public boolean checkDataExistance(String userName, String emailId, String mobileNo) throws DataAccessException {
		PartnerInfo partnerInfo = partnerRepository.findByUserNameOrEmailIdOrMobileNoAllIgnoreCase(userName, emailId, mobileNo);
		if(!CommonUtil.isNull(partnerInfo)) {
			return true;
		}
		
		return false;
	}
	
	@Override
	public PartnerInfo createPartner(PartnerInfo partnerInfo) throws DataAccessException {
		return partnerRepository.save(partnerInfo);
	}

	@Override
	public PartnerInfo isValidPartner(String userName, String emailId, String password) throws DataAccessException {
		return partnerRepository.findByUserNameOrEmailIdAndPassword(userName, emailId, password);
	}
	
	@Override
	public PartnerInfo getSpecificPartner(Long partnerId) throws DataAccessException {
		return partnerRepository.findOne(isIdEq(partnerId));
	}

	@Override
	public PartnerInfo updatePartnerInfo(PartnerInfo partnerInfo) throws DataAccessException {
		return partnerRepository.save(partnerInfo);
	}
	
	@Override
	public int updateLoginDateTime(Long id, Timestamp loginTime) {
		return partnerRepository.updateLoginDateTime(id, loginTime);
	}
	
	@Override
	public int updatePassword(Long id, String password, String updatedBy, Timestamp updatedDate) {
		return partnerRepository.updatePassword(id, password, updatedBy, updatedDate);
	}
	
	@Override
	public PartnerInfo findPartnerByEmailId(String emailId)	throws DataAccessException {
		return partnerRepository.findByEmailIdAllIgnoreCase(emailId);
	}

	@Override
	public PartnerDTO searchPartners(PartnerDTO partnerDto) throws DataAccessException {

		Integer offset = 0;
		Integer limit = Constants.DEFAULT_PAGE_SIZE;
		
		Integer totalRecords = 0;
		
		Integer pageIndex = 1;
		Integer pageSize = Constants.DEFAULT_PAGE_SIZE;
		
		PaginationBean paginationBean = new PaginationBean();
		
		if(null != partnerDto.getPaginationBean()) {
			paginationBean = partnerDto.getPaginationBean();
			pageIndex = paginationBean.getPageIndex();
			pageSize = paginationBean.getPageSize();
			totalRecords = paginationBean.getNoOfRecords();
		} else {
			totalRecords = getTotalNumberOfRecords(partnerDto);
			paginationBean.setNoOfRecords(totalRecords);
			partnerDto.setPaginationBean(paginationBean);
		}
		
		if (pageIndex != null && pageSize != null) {
			offset = (pageIndex - 1) * pageSize;
			limit = pageSize;
		}
		
		if(null != partnerDto.getReportPropertiesBean() && partnerDto.getReportPropertiesBean().isToFetchAllRecords()) {
			offset = 0;
			limit = totalRecords;
		}
		
		boolean canDoWildCardSearch = false;
		
		boolean toOrderByAsc = false;
		
		if(!CommonUtil.isNull(partnerDto.getReportPropertiesBean())) {
			if(partnerDto.getReportPropertiesBean().isToDoWildCardSearch()) {
				canDoWildCardSearch = true;
			} else if(partnerDto.getReportPropertiesBean().isToOrderByAsc()) {
				toOrderByAsc = true;
			}
		}

		List<PartnerDTO> partnerInfoList = new ArrayList<>();
		
		JPAQuery query = new JPAQuery(entityManager);
		
		QPartnerInfo partnerInfo = QPartnerInfo.partnerInfo;
		
		List<Tuple> dataList = query
				.from(partnerInfo)
				.where(isUserNameEqOrLike(partnerDto.getUserName(), canDoWildCardSearch),
						isEmailIdEqOrLike(partnerDto.getEmailId(), canDoWildCardSearch),
						isFirstNameEqOrLike(partnerDto.getFirstName(), canDoWildCardSearch),
						isLastNameEqOrLike(partnerDto.getLastName(), canDoWildCardSearch),
						isMobileNoEqOrLike(partnerDto.getMobileNo(), canDoWildCardSearch),
						isOrgNameEqOrLike(partnerDto.getOrganizationName(), canDoWildCardSearch),
						isCountryEq(partnerDto.getCountry()),
						isStateEq(partnerDto.getState()),
						isCitysEq(partnerDto.getCity()),
						isPartnerTypeEq(partnerDto.getPartnerType()),
						isStatusEq(partnerDto.getStatus()))
				.offset(offset)
				.limit(limit)
				.orderBy(orderById(toOrderByAsc))
				.list(partnerInfo.id,
					  partnerInfo.userName,
					  partnerInfo.firstName,
					  partnerInfo.lastName,
					  partnerInfo.emailId,
					  partnerInfo.mobileNo,
					  partnerInfo.alternativeNo,
					  partnerInfo.country,
					  partnerInfo.state,
					  partnerInfo.city,
					  partnerInfo.partnerType,
					  partnerInfo.organizationName,
					  partnerInfo.status,
					  partnerInfo.merchantDesc,
					  partnerInfo.websiteAddress,
					  partnerInfo.lastLoginDateTime);
				
		PartnerDTO resPartnerDTO = null;
		
		for (Tuple tuple : dataList) {
			
			resPartnerDTO = new PartnerDTO();
			
			resPartnerDTO.setId(tuple.get(partnerInfo.id));
			resPartnerDTO.setUserName(tuple.get(partnerInfo.userName));
			resPartnerDTO.setFirstName(tuple.get(partnerInfo.firstName));
			resPartnerDTO.setLastName(tuple.get(partnerInfo.lastName));
			resPartnerDTO.setEmailId(tuple.get(partnerInfo.emailId));
			resPartnerDTO.setMobileNo(tuple.get(partnerInfo.mobileNo));
			resPartnerDTO.setAlternativeNo(tuple.get(partnerInfo.alternativeNo));
			resPartnerDTO.setCountry(tuple.get(partnerInfo.country));
			resPartnerDTO.setState(tuple.get(partnerInfo.state));
			resPartnerDTO.setCity(tuple.get(partnerInfo.city));
			resPartnerDTO.setPartnerType(tuple.get(partnerInfo.partnerType));
			resPartnerDTO.setOrganizationName(tuple.get(partnerInfo.organizationName));
			resPartnerDTO.setStatus(tuple.get(partnerInfo.status));
			resPartnerDTO.setMerchantDesc(tuple.get(partnerInfo.merchantDesc));
			resPartnerDTO.setWebsiteAddress(tuple.get(partnerInfo.websiteAddress));
			resPartnerDTO.setLastLoginDateTime(tuple.get(partnerInfo.lastLoginDateTime));
			
			partnerInfoList.add(resPartnerDTO);
		}
		
		partnerDto.setPartnerInfoList(partnerInfoList);
		
		return partnerDto;
		
	}
	
	private int getTotalNumberOfRecords(PartnerDTO partnerDto) {
		
		boolean canDoWildCardSearch = false;
		
		boolean toOrderByAsc = false;
		
		if(!CommonUtil.isNull(partnerDto.getReportPropertiesBean())) {
			if(partnerDto.getReportPropertiesBean().isToDoWildCardSearch()) {
				canDoWildCardSearch = true;
			} else if(partnerDto.getReportPropertiesBean().isToOrderByAsc()) {
				toOrderByAsc = true;
			}
		}
		
		JPAQuery query = new JPAQuery(entityManager);
		
		QPartnerInfo partnerInfo = QPartnerInfo.partnerInfo;
		
		List<Long> partnersList = query
				.from(partnerInfo)
				.where(isUserNameEqOrLike(partnerDto.getUserName(), canDoWildCardSearch),
						isEmailIdEqOrLike(partnerDto.getEmailId(), canDoWildCardSearch),
						isFirstNameEqOrLike(partnerDto.getFirstName(), canDoWildCardSearch),
						isLastNameEqOrLike(partnerDto.getLastName(), canDoWildCardSearch),
						isMobileNoEqOrLike(partnerDto.getMobileNo(), canDoWildCardSearch),
						isOrgNameEqOrLike(partnerDto.getOrganizationName(), canDoWildCardSearch),
						isCountryEq(partnerDto.getCountry()),
						isStateEq(partnerDto.getState()),
						isCitysEq(partnerDto.getCity()),
						isPartnerTypeEq(partnerDto.getPartnerType()),
						isStatusEq(partnerDto.getStatus()))
				.orderBy(orderById(toOrderByAsc))
				.list(partnerInfo.id);

		return (partnersList != null && !partnersList.isEmpty() ? partnersList.size() : 0);
		
	}
	
	private BooleanExpression isUserNameEqOrLike(String userName, boolean doWildCardSearch) {
		if(doWildCardSearch) {
			return (!StringUtil.isNullAndEmpty(userName)) ? QPartnerInfo.partnerInfo.userName.toUpperCase().like("%" + userName.toUpperCase().replace("*", "") + "%") : null;
		} else {
			return (!StringUtil.isNullAndEmpty(userName)) ? QPartnerInfo.partnerInfo.userName.eq(userName) : null;
		}
	}
	
	private BooleanExpression isEmailIdEqOrLike(String emailId, boolean doWildCardSearch) {
		if(doWildCardSearch) {
			return (!StringUtil.isNullAndEmpty(emailId)) ? QPartnerInfo.partnerInfo.emailId.toUpperCase().like("%" + emailId.toUpperCase().replace("*", "") + "%") : null;
		} else {
			return (!StringUtil.isNullAndEmpty(emailId)) ? QPartnerInfo.partnerInfo.emailId.eq(emailId) : null;
		}
	}
	
	private BooleanExpression isFirstNameEqOrLike(String firstName, boolean doWildCardSearch) {
		if(doWildCardSearch) {
			return (!StringUtil.isNullAndEmpty(firstName)) ? QPartnerInfo.partnerInfo.firstName.toUpperCase().like("%" + firstName.toUpperCase().replace("*", "") + "%") : null;
		} else {
			return (!StringUtil.isNullAndEmpty(firstName)) ? QPartnerInfo.partnerInfo.firstName.eq(firstName) : null;
		}
	}
	
	private BooleanExpression isLastNameEqOrLike(String lastName, boolean doWildCardSearch) {
		if(doWildCardSearch) {
			return (!StringUtil.isNullAndEmpty(lastName)) ? QPartnerInfo.partnerInfo.lastName.toUpperCase().like("%" + lastName.toUpperCase().replace("*", "") + "%") : null;
		} else {
			return (!StringUtil.isNullAndEmpty(lastName)) ? QPartnerInfo.partnerInfo.lastName.eq(lastName) : null;
		}
	}

	private BooleanExpression isMobileNoEqOrLike(String mobileNo, boolean doWildCardSearch) {
		if(doWildCardSearch) {
			return (!StringUtil.isNullAndEmpty(mobileNo)) ? QPartnerInfo.partnerInfo.mobileNo.toUpperCase().like("%" + mobileNo.toUpperCase().replace("*", "") + "%") : null;
		} else {
			return (!StringUtil.isNullAndEmpty(mobileNo)) ? QPartnerInfo.partnerInfo.mobileNo.eq(mobileNo) : null;
		}
	}
	
	private BooleanExpression isOrgNameEqOrLike(String orgName, boolean doWildCardSearch) {
		if(doWildCardSearch) {
			return (!StringUtil.isNullAndEmpty(orgName)) ? QPartnerInfo.partnerInfo.organizationName.toUpperCase().like("%" + orgName.toUpperCase().replace("*", "") + "%") : null;
		} else {
			return (!StringUtil.isNullAndEmpty(orgName)) ? QPartnerInfo.partnerInfo.organizationName.eq(orgName) : null;
		}
	}
	
	private BooleanExpression isStatusEq(String status) {
		return (!StringUtil.isNullAndEmpty(status)) ? QPartnerInfo.partnerInfo.status.eq(status) : null;
	}
	
	private BooleanExpression isCountryEq(String country) {
		return (!StringUtil.isNullAndEmpty(country)) ? QPartnerInfo.partnerInfo.country.eq(country) : null;
	}
	
	private BooleanExpression isCitysEq(String city) {
		return (!StringUtil.isNullAndEmpty(city)) ? QPartnerInfo.partnerInfo.city.eq(city) : null;
	}

	private BooleanExpression isStateEq(String state) {
		return (!StringUtil.isNullAndEmpty(state)) ? QPartnerInfo.partnerInfo.state.eq(state) : null;
	}
	
	private BooleanExpression isPartnerTypeEq(String partnerType) {
		return (!StringUtil.isNullAndEmpty(partnerType)) ? QPartnerInfo.partnerInfo.partnerType.eq(partnerType) : null;
	}

	private OrderSpecifier<Long> orderById(boolean isToOrderByAsc) {
		if(isToOrderByAsc) {
			return QPartnerInfo.partnerInfo.id.asc();
		} else {
			return QPartnerInfo.partnerInfo.id.desc();
		}
	}
	
	private BooleanExpression isIdEq(Long adminId) {
		return (!CommonUtil.isNull(adminId)) ? QPartnerInfo.partnerInfo.id.eq(adminId) : null;
	}

}