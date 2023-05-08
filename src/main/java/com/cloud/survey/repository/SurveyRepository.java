package com.cloud.survey.repository;

import com.cloud.survey.entity.IsYn;
import com.cloud.survey.entity.Survey;
import com.cloud.survey.entity.SurveyStatus;
//import com.cloud.survey.querydsl.SurveyRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface SurveyRepository extends JpaRepository<Survey, Integer> {

    List<Survey> findByStatusAndIsPrivateYn(SurveyStatus status, IsYn isPrivateYn);

    @Query(value =
            "SELECT sc.content, s.*, " +
            "(select count(*) " +
            "from question q left join answer a on q.que_id = a.que_id and q.sur_id = s.sur_id " +
            "where a.del_yn <> 'N' " +
            "and a.que_id = (SELECT min(q.que_id) FROM question qu where sur_id=s.sur_id)) answer_cnt " +
            "FROM survey s left join survey_category sc on sc.sur_cat_id = s.category_id " +
            "WHERE 1=1 " +
            "and s.status = 'I' " +
//            "and s.due_dt < now() " +
            "and s.is_private = 'N' "
//            "and s.category_id = :categoryId "
//            "and status = :#{#status?.name()}"
            , nativeQuery = true)
    Page<Map<String,Object>> findByCategoryIdAndStatus(@Param("categoryId") int categoryId, Pageable pageable);


    @Query(value =
            "SELECT" +
            "    sc.content, " +
            "    case " +
            "        when s.status = 'P' then '제작' " +
            "        when s.status = 'I' && due_dt > now() then '배포' " +
            "        when s.status = 'I' && due_dt < now() then '마감' " +
            "        end status_name, " +
            "    if(s.status = 'I' && due_dt < now(), 'F', s.status) display_status, " +
            "    s.* " +
            "FROM survey s left join survey_category sc on sc.sur_cat_id = s.category_id " +
            "WHERE 1=1 " +
            "and s.status <> 'D'" +
//            "and s.category_id = :categoryId " +
//            "and s.status = :#{#status?.name()} " +
            "and s.reg_id = :regId", nativeQuery = true)
    Page<Map<String,Object>> findByCategoryIdAndRegId(@Param("regId") String regId, Pageable pageable);


    @Query(value =
            "SELECT" +
            "    sc.content, " +
            "    case " +
            "        when s.status = 'P' then '제작' " +
            "        when s.status = 'I' && due_dt > now() then '배포' " +
            "        when s.status = 'I' && due_dt < now() then '마감' " +
            "        end status_name, " +
            "    if(s.status = 'I' && due_dt < now(), 'F', s.status) display_status, " +
            "    s.* " +
            "FROM survey s left join survey_category sc on sc.sur_cat_id = s.category_id " +
            "    left outer join question q on s.sur_id = q.sur_id  join answer a on q.que_id = a.que_id and a.reg_id = :regId " +
            "WHERE 1=1 " +
            "and s.status <> 'D' " +
//            "and s.category_id = :categoryId " +
//            "and s.status = :#{#status?.name()} " +
            "group by s.sur_id ", nativeQuery = true)
    Page<Map<String,Object>> findByCategoryIdAndRegIdAndStatus(@Param("regId") String regId, Pageable pageable);


    Survey findBySurId(int surId);

    @Query(value = "SELECT * FROM survey " +
            "WHERE category_id=:surCatId " +
            "ORDER BY views DESC, reg_dt DESC LIMIT 1;", nativeQuery = true)
    Survey findBestSurveyByCategory(Integer surCatId);


    @Query("select s.surId from Survey s where s.surveyCategory.surCatId = :surCatId")
    List<Integer> findSurIdBySurCatId(Integer surCatId);
}
