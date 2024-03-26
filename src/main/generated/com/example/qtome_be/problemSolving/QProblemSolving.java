package com.example.qtome_be.problemSolving;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProblemSolving is a Querydsl query type for ProblemSolving
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProblemSolving extends EntityPathBase<ProblemSolving> {

    private static final long serialVersionUID = -894919057L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProblemSolving problemSolving = new QProblemSolving("problemSolving");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.example.qtome_be.member.QMember member;

    public final com.example.qtome_be.multipleChoice.QMultipleChoice multipleChoice;

    public final com.example.qtome_be.question.QQuestion question;

    public QProblemSolving(String variable) {
        this(ProblemSolving.class, forVariable(variable), INITS);
    }

    public QProblemSolving(Path<? extends ProblemSolving> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProblemSolving(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProblemSolving(PathMetadata metadata, PathInits inits) {
        this(ProblemSolving.class, metadata, inits);
    }

    public QProblemSolving(Class<? extends ProblemSolving> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.example.qtome_be.member.QMember(forProperty("member")) : null;
        this.multipleChoice = inits.isInitialized("multipleChoice") ? new com.example.qtome_be.multipleChoice.QMultipleChoice(forProperty("multipleChoice"), inits.get("multipleChoice")) : null;
        this.question = inits.isInitialized("question") ? new com.example.qtome_be.question.QQuestion(forProperty("question"), inits.get("question")) : null;
    }

}

