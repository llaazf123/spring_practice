package kr.co.uclick.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QSample is a Querydsl query type for Sample
 */
//UML에서 Java로(UML-to-Java) 변환은 생성된 특정 요소에 @generated 태그를 추가
@Generated("com.querydsl.codegen.EntitySerializer")	//querydsl 사용




public class QSample extends EntityPathBase<Sample> {

    private static final long serialVersionUID = 1813199477L;	//노란색 warning을 없애주기 위해

    public static final QSample sample = new QSample("sample");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final NumberPath<Integer> number = createNumber("number", Integer.class);

    public QSample(String variable) {
        super(Sample.class, forVariable(variable));
    }

    public QSample(Path<? extends Sample> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSample(PathMetadata metadata) {
        super(Sample.class, metadata);
    }

}

