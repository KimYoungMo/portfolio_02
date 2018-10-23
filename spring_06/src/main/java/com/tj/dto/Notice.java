package com.tj.dto;

import java.sql.Date;

import org.apache.ibatis.type.Alias;

@Alias("Notice")
public class Notice {

	private int typeSeq;
	private int boardSeq;
	private String memberId;
	private String memberNick;
	private String title;
	private String content;
	private int hits;
	private Date createDate;
	private Date updateDate;
	@Override
	public String toString() {
		return "Notice [typeSeq=" + typeSeq + ", boardSeq=" + boardSeq + ", memberId=" + memberId + ", memberNick="
				+ memberNick + ", title=" + title + ", content=" + content + ", hits=" + hits + ", createDate="
				+ createDate + ", updateDate=" + updateDate + "]";
	}
	public int getTypeSeq() {
		return typeSeq;
	}
	public void setTypeSeq(int typeSeq) {
		this.typeSeq = typeSeq;
	}
	public int getBoardSeq() {
		return boardSeq;
	}
	public void setBoardSeq(int boardSeq) {
		this.boardSeq = boardSeq;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberNick() {
		return memberNick;
	}
	public void setMemberNick(String memberNick) {
		this.memberNick = memberNick;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getHits() {
		return hits;
	}
	public void setHits(int hits) {
		this.hits = hits;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
}
