package Impl;

import java.util.List;

import dto.DiaryDto;
import dto.DiarycommentDto;
import dto.JournalDto;

public interface DiaryImpl {
	


	public boolean addJournal(JournalDto dto);
	
	public int getCountJournal();
	
	public boolean addDiary(DiaryDto dto);
	
	public List<JournalDto> getJournalList(int page);
	
	public boolean delDiary(int seq);
	
	public DiaryDto getDiary(int seq); 
	
	public boolean updateDiary(DiaryDto dto);

	public JournalDto getJournalDto(int seq);
	
	public int getDiaryCount(String startdate,String enddate,String id);
	

	public int getJournalSeq(String tday);
	
	public int CommantWrite(int seq,String id, String dcomment);

	
	public int checkJournal(String startDate,String endDate,String id);
	
	public List<DiarycommentDto> Commantview(int seq);
	
	public int CommentDelete(int seq);
	
	public boolean CommentDeletes(int jseq);
	
	public List<JournalDto> getSearchJournalList(String stext, int page);
	
	public int getSearchCountJournal(String stext);
	
	public boolean changeDiariesJour_Check_zero(String id, String startDate,String endDate);
	
	public boolean deleteJournal(int seq);

	public void addLike(int seq, String id);

	public int countMyJournal(String id);
	
	
	public List<JournalDto> myJournalList(String id, int page);

	public List<DiaryDto> getDiaryList(String startdate, String enddate, String id);



	public void countLike(int seq);
	
	public int Likecheack(int seq,String loginid);

	public void deleteLike(int seq,String loginid);
	
	public void countLikedel(int seq);

	public List<JournalDto> getBestJournal();

}

