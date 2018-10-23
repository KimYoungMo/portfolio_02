package com.tj.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.tj.dao.NoticeDao;

public class NoticeDaoImpl implements NoticeDao {

	Logger logger = Logger.getLogger(BoardDaoImpl.class);
	@Autowired private JdbcTemplate jdbcTemplate;

	@Override
	public int writeCont(HashMap<String, Object> params) {
		
		String sql = "INSERT INTO board (`type_seq`, `member_id`, `member_nick`, `title`, `content`, `create_date`)";
		sql = sql.concat(" VALUES (?, ?, ?, ?, ?, NOW() );");
		

		return jdbcTemplate.update(sql,
				params.get("typeSeq"),
				params.get("memberId"),
				params.get("memberNick"),
				params.get("title"),
				params.get("contents")
		);
	}

	@Override
	public List<Map<String, Object>> bringCont(HashMap<String, String> params) {
		String sql = "SELECT * FROM board ";
		sql += "WHERE type_seq = '2' ";
		String searchType = (params.get("searchType") == null)?"":params.get("searchType");
		logger.debug("searchType : " + searchType );
		if(searchType.equals("2")){
			sql += "AND title LIKE CONCAT('%', ?, '%') ";
		} else if(searchType.equals("3")) {
			sql += "AND content LIKE CONCAT('%', ?, '%') ";
		} else if(searchType.equals("1")) {
			sql += "AND title LIKE CONCAT('%', ?, '%') OR content LIKE CONCAT('%', ?, '%') ";
		}
		sql += "ORDER BY board_seq DESC LIMIT ?, ?";
		
		Object[] args = null;
		
		if(searchType.equals("")) {
			args = new Object[] {
					Integer.parseInt(params.get("startIdx")),
					Integer.parseInt(params.get("pageArticleSize")) };
		} else if(searchType.equals("2")) {
			args = new Object[] {
					params.get("searchText"),
					Integer.parseInt(params.get("startIdx")),
					Integer.parseInt(params.get("pageArticleSize")) };
		} else if(searchType.equals("3")) {
			args = new Object[] {
					params.get("searchText"),
					Integer.parseInt(params.get("startIdx")),
					Integer.parseInt(params.get("pageArticleSize")) };
		} else if(searchType.equals("1")) {
			args = new Object[] {
					params.get("searchText"),
					params.get("searchText"),
					Integer.parseInt(params.get("startIdx")),
					Integer.parseInt(params.get("pageArticleSize")) };
		}
//		List<Board> boards = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Board>(Board.class));
		
		List<Map<String, Object>> allList = jdbcTemplate.queryForList(sql, args);
		
		
		return allList;
	}

	@Override
	public int getTotalArticleCnt(HashMap<String, String> params) {
		String sql = "SELECT COUNT(board_seq) FROM board ";
		sql += "WHERE type_seq = '2' ";
		String searchType = (params.get("searchType") == null)?"":params.get("searchType");
		
		if(searchType.equals("2")){
			sql += "AND title LIKE CONCAT('%', ?, '%') ";
		} else if(searchType.equals("3")) {
			sql += "AND content LIKE CONCAT('%', ?, '%') ";
		} else if(searchType.equals("1")) {
			sql += "AND title LIKE CONCAT('%', ?, '%') OR content LIKE CONCAT('%', ?, '%') ";
		}
		
		Object[] args = null;
		
		if(searchType.equals("2")) {
			args = new Object[] {
					params.get("searchText")};
		} else if(searchType.equals("3")) {
			args = new Object[] {
					params.get("searchText")};
		} else if(searchType.equals("1")) {
			args = new Object[] {
					params.get("searchText"),
					params.get("searchText")};
		}
	
		return jdbcTemplate.queryForObject(sql, args, Integer.class);
	}

	@Override
	public int updateHits(int boardSeq, int typeSeq) {
		
		String sql = "UPDATE board SET hits = hits + 1 WHERE board_seq = ? AND type_seq = ?";
		
		return jdbcTemplate.update(sql, boardSeq, typeSeq);
	}

	@Override
	public Map<String, Object> getBoard(int boardSeq, int typeSeq) {
		
		String sql = "SELECT * FROM board WHERE board_seq = ? AND type_seq = ?";
		Map<String, Object> map = jdbcTemplate.queryForMap(sql, new Integer[] { boardSeq, typeSeq });
		return map;
	}

	@Override
	public int delete(int boardSeq, int typeSeq) {
		
		String sql = "DELETE FROM board WHERE board_seq = ? AND type_seq = ?";
			
		return jdbcTemplate.update(sql, boardSeq, typeSeq);
	}

	@Override
	public int update(HashMap<String, Object> params) {
		
		String sql = "UPDATE board SET title=?, content=?, update_date = NOW() WHERE board_seq = ? AND type_seq = ?";
		
		return jdbcTemplate.update(sql, 
				params.get("title"),
				params.get("content"),
				params.get("boardSeq"),
				params.get("typeSeq")
				);
	}	
}
