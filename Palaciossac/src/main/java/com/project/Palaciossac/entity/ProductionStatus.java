package com.project.Palaciossac.entity;

/**
 * Ciclo de vida de una orden de producción (columna "estado").
 * PENDIENTE -> EN_PROCESO -> FINALIZADA, o CANCELADA desde PENDIENTE / EN_PROCESO.
 */
public enum ProductionStatus {
    PENDIENTE,
    EN_PROCESO,
    FINALIZADA,
    CANCELADA
}
