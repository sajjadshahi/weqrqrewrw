package com.shm.distribute.service.distributable;

import com.shm.common.dto.base.ResultDto;

public interface IDistributable {
    public ResultDto distribute();
    public ResultDto cancel();

}
