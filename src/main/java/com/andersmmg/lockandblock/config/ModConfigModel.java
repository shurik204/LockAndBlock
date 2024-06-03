package com.andersmmg.lockandblock.config;

import com.andersmmg.lockandblock.LockAndBlock;
import io.wispforest.owo.config.annotation.Config;
import io.wispforest.owo.config.annotation.Modmenu;
import io.wispforest.owo.config.annotation.RangeConstraint;

@Modmenu(modId = LockAndBlock.MOD_ID)
@Config(name = LockAndBlock.MOD_ID, wrapperName = "ModConfig")
public class ModConfigModel {
    @RangeConstraint(min = 1.0f, max = 10.0f)
    public float teslaCoilDamage = 3.0f;
    @RangeConstraint(min = 1.0f, max = 10.0f)
    public float teslaCoilRange = 2.0f;
    @RangeConstraint(min = 3, max = 20)
    public int maxForceFieldLength = 7;
    @RangeConstraint(min = 1.0f, max = 10.0f)
    public float playerSensorRange = 4.0f;
    @RangeConstraint(min = 3, max = 30)
    public int maxTripMineDistance = 7;
    @RangeConstraint(min = 3, max = 30)
    public int maxLaserSensorDistance = 10;
    @RangeConstraint(min = 1.0f, max = 10.0f)
    public float redstoneLaserDamage = 3.0f;

    @SuppressWarnings("unused")
    public boolean allowTripMinesAir = true;
    @SuppressWarnings("unused")
    public boolean allowLaserInAir = true;
}