package com.xiaoxu.xiaoxu_ec.main_delegates.sort.content;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * Created by xiaoxu on 2017/8/30.
 */

public class SectionHeadEntity extends SectionEntity<SectionContentItemEntity> {

    private boolean mIsMore = false;
    private int mId = -1;

    public SectionHeadEntity(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public SectionHeadEntity(SectionContentItemEntity sectionContentItemEntity) {
        super(sectionContentItemEntity);
    }

    public boolean isMore() {
        return mIsMore;
    }

    public void setIsMore(boolean isMore) {
        this.mIsMore = isMore;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        this.mId = id;
    }
}
