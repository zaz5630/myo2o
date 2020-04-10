package cn.azzhu.myo2o.entity;

import java.util.Date;

//顾客已领取的奖品映射
public class UserAwardMap {

	private Long userAwardId;	//主键id
	private Long userId;	//用户id
	private Long awardId;	//奖品id
	private Long shopId;	//所属店铺id
	private String userName;	//用户名
	private String awardName;	//奖品名
	private Date expireTime;	//过期时间
	private Date createTime;	//创建时间
	private Integer usedStatus;	//使用状态 0.未兑换 1.已兑换
	private Integer point;	//领取奖品所消耗的积分
	private PersonInfo user;	//操作员息实体类
	private Award award;	//奖品信息实体类
	private Shop shop;		//店铺信息实体类

	public Long getUserAwardId() {
		return userAwardId;
	}

	public void setUserAwardId(Long userAwardId) {
		this.userAwardId = userAwardId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getAwardId() {
		return awardId;
	}

	public void setAwardId(Long awardId) {
		this.awardId = awardId;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAwardName() {
		return awardName;
	}

	public void setAwardName(String awardName) {
		this.awardName = awardName;
	}

	public Date getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getUsedStatus() {
		return usedStatus;
	}

	public void setUsedStatus(Integer usedStatus) {
		this.usedStatus = usedStatus;
	}

	public Integer getPoint() {
		return point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}

	public PersonInfo getUser() {
		return user;
	}

	public void setUser(PersonInfo user) {
		this.user = user;
	}

	public Award getAward() {
		return award;
	}

	public void setAward(Award award) {
		this.award = award;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

}
