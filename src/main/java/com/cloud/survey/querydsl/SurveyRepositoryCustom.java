//package com.cloud.survey.querydsl;
//
////import com.cloud.survey.entity.QSurvey;
////import com.cloud.survey.entity.QSurveyCategory;
//import com.cloud.survey.entity.Survey;
//import com.cloud.survey.entity.SurveyStatus;
//import com.querydsl.core.QueryResults;
//import com.querydsl.core.Tuple;
//import com.querydsl.core.types.ExpressionUtils;
//import com.querydsl.core.types.Projections;
//import com.querydsl.core.types.dsl.BooleanExpression;
//import com.querydsl.core.types.dsl.CaseBuilder;
//import com.querydsl.jpa.JPAExpressions;
//import com.querydsl.jpa.JPQLQuery;
//import com.querydsl.jpa.impl.JPAQueryFactory;
//import lombok.RequiredArgsConstructor;
//import org.apache.commons.lang.StringUtils;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Repository;
//
//import javax.persistence.EntityManager;
//import java.time.LocalDateTime;
//import java.util.Date;
//import java.util.List;
//import java.util.Map;
//
//@RequiredArgsConstructor
//@Repository
//public class SurveyRepositoryCustom {
//
//
//    private final JPAQueryFactory queryFactory;
//    private final EntityManager entityManager;
//    QSurvey qSurvey = QSurvey.survey;
//    QSurveyCategory qSurveyCategory = QSurveyCategory.surveyCategory;
//
//
//    public List<Tuple> findByCategoryIdAndTitle( // 검색리스트
//            String title, Integer categoryId, Pageable pageable) {
//
//        List<Tuple> results = queryFactory
//                .select(qSurvey, qSurveyCategory.content
////                        ExpressionUtils.as(
////                                JPAExpressions.select(m.team.count())
////                                    .from(m)
////                                    .where(m.team.eq(t)),
////                                "memberCount")
//                )
//                .from(qSurvey)
//                .leftJoin(qSurveyCategory)
//                .on(qSurvey.surveyCategory.surCatId.eq(qSurveyCategory.surCatId))
//                .where(
//                        eqCategoryId(categoryId),
//                        qSurvey.title.like("%"+title+"%")
//                )
//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize())
//                .fetch();
//
//        return results;
//    }
//
//    public List<Tuple> findByRegIdAndCategoryIdAndStatusAndTitle( //생성목록
//            String title, String regId, Integer categoryId, SurveyStatus status, Pageable pageable) {
//
//        List<Tuple> results = queryFactory
//                .select(qSurvey, qSurveyCategory.content
////                        ExpressionUtils.as(
////                                JPAExpressions.select(m.team.count())
////                                    .from(m)
////                                    .where(m.team.eq(t)),
////                                "memberCount")
//                )
//                .from(qSurvey)
//                .leftJoin(qSurveyCategory)
//                .on(qSurvey.surveyCategory.surCatId.eq(qSurveyCategory.surCatId))
//                .where(
//                        eqRegId(regId),
//                        eqCategoryId(categoryId),
//                        qSurvey.title.like("%"+title+"%")
//                )
//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize())
//                .fetch();
//
//        return results;
//    }
//
//    public List<Tuple> findByCategoryIdAndStatusAndTitle( // 참여목록
//            String title, String regId, Integer categoryId, SurveyStatus status, Pageable pageable) {
//
//        var date = LocalDateTime.now();
//
//        List<Tuple> results = queryFactory
//                .select(qSurvey, qSurveyCategory.content
//                        , new CaseBuilder()
//                                .when(qSurvey.status.eq(SurveyStatus.valueOf("P"))).then("제작")
//                                .when(qSurvey.status.eq(SurveyStatus.valueOf("I"))).then("배포")
//                                .otherwise("")
////
//                )
//                .from(qSurvey)
//                .leftJoin(qSurveyCategory)
//                .on(qSurvey.surveyCategory.surCatId.eq(qSurveyCategory.surCatId))
//                .where(
//                        eqRegId(regId),
//                        eqCategoryId(categoryId),
//                        eqStatus(status),
//                        qSurvey.title.like("%"+title+"%")
//                )
//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize())
//                .fetch();
//
//
//
//
//
//        return results;
//
//
//    }
//
//
//    private BooleanExpression eqRegId(String regId) {
//        if (StringUtils.isEmpty(regId)) {
//            return null;
//        }
//        return qSurvey.regId.eq(regId);
//    }
//
//    private BooleanExpression eqCategoryId(Integer cateId) {
//        if (cateId == null) {
//            return null;
//        }
//        return qSurvey.surveyCategory.surCatId.eq(cateId);
//    }
//
//    private BooleanExpression eqStatus(SurveyStatus status) {
//        if (StringUtils.isEmpty(String.valueOf(status))) {
//            return null;
//        }
//        return qSurvey.status.eq(status);
//    }
//}
