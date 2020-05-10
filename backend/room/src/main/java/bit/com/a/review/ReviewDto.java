package bit.com.a.review;

import java.io.Serializable;
//import java.sql.Date;

import bit.com.a.user.UserDto;

public class ReviewDto implements Serializable {

    private int id;

    private String content;

    private int grade; // 리뷰 평가 등급(3:최고예요, 2:좋아요, 1:괜찮아요, 0:글쎄요)

    private int reviewCount; // 총 리뷰 수

    private int del; // 삭제 유무(0 / 1)

    private int ref; // 그룹번호

    private int step; // 행(row) 번호

    private int depth; // 깊이

    private String userId;

    private String brokerId;

    private int auth;

    private String profileImage; // 프로필 사진

    private String createdAt; // 작성일

    private String createdBy;

    private String updatedAt;

    private String updatedBy;

    private String deletedAt;

    private String deletedBy;

    private String brokerReviewId;

    private UserDto userInformation;

    private String status;

    private int bestCount;

    private int goodCount;

    public int getGoodCount() {
        return this.goodCount;
    }

    public void setGoodCount(int goodCount) {
        this.goodCount = goodCount;
    }

    private int sosoCount;

    private int nopeCount;

    public int getBestCount() {
        return this.bestCount;
    }

    public void setBestCount(int bestCount) {
        this.bestCount = bestCount;
    }

    public int getSosoCount() {
        return this.sosoCount;
    }

    public void setSosoCount(int sosoCount) {
        this.sosoCount = sosoCount;
    }

    public int getNopeCount() {
        return this.nopeCount;
    }

    public void setNopeCount(int nopeCount) {
        this.nopeCount = nopeCount;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UserDto getUserInformation() {
        return this.userInformation;
    }

    public void setUserInformation(UserDto userInformation) {
        this.userInformation = userInformation;
    }

    public String getBrokerReviewId() {
        return this.brokerReviewId;
    }

    public void setBrokerReviewId(String brokerReviewId) {
        this.brokerReviewId = brokerReviewId;
    }

    public ReviewDto() {
    }

    public ReviewDto(int id, String content, int grade, int reviewCount, int del, int ref, int step, int depth,
            String userId, String brokerId, int auth, String profileImage, String createdAt, String createdBy,
            String updatedAt, String updatedBy, String deletedAt, String deletedBy) {
        this.id = id;
        this.content = content;
        this.grade = grade;
        this.reviewCount = reviewCount;
        this.del = del;
        this.ref = ref;
        this.step = step;
        this.depth = depth;
        this.userId = userId;
        this.brokerId = brokerId;
        this.auth = auth;
        this.profileImage = profileImage;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.updatedAt = updatedAt;
        this.updatedBy = updatedBy;
        this.deletedAt = deletedAt;
        this.deletedBy = deletedBy;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getGrade() {
        return this.grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getReviewCount() {
        return this.reviewCount;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }

    public int getDel() {
        return this.del;
    }

    public void setDel(int del) {
        this.del = del;
    }

    public int getRef() {
        return this.ref;
    }

    public void setRef(int ref) {
        this.ref = ref;
    }

    public int getStep() {
        return this.step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public int getDepth() {
        return this.depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBrokerId() {
        return this.brokerId;
    }

    public void setBrokerId(String brokerId) {
        this.brokerId = brokerId;
    }

    public int getAuth() {
        return this.auth;
    }

    public void setAuth(int auth) {
        this.auth = auth;
    }

    public String getProfileImage() {
        return this.profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUpdatedBy() {
        return this.updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getDeletedAt() {
        return this.deletedAt;
    }

    public void setDeletedAt(String deletedAt) {
        this.deletedAt = deletedAt;
    }

    public String getDeletedBy() {
        return this.deletedBy;
    }

    public void setDeletedBy(String deletedBy) {
        this.deletedBy = deletedBy;
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", content='" + getContent() + "'" + ", grade='" + getGrade() + "'"
                + ", reviewCount='" + getReviewCount() + "'" + ", del='" + getDel() + "'" + ", ref='" + getRef() + "'"
                + ", step='" + getStep() + "'" + ", depth='" + getDepth() + "'" + ", userId='" + getUserId() + "'"
                + ", brokerId='" + getBrokerId() + "'" + ", auth='" + getAuth() + "'" + ", profileImage='"
                + getProfileImage() + "'" + ", createdAt='" + getCreatedAt() + "'" + ", createdBy='" + getCreatedBy()
                + "'" + ", updatedAt='" + getUpdatedAt() + "'" + ", updatedBy='" + getUpdatedBy() + "'"
                + ", deletedAt='" + getDeletedAt() + "'" + ", deletedBy='" + getDeletedBy() + "'" + "}";
    }

}