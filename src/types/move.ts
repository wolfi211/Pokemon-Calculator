import type { Type } from "./type";

export interface Move {
  id: number;
  name: string;
  type: Type | number | undefined;
}

export const toMove = (row: any): Move => ({
    id: row.id,
    name: row.name,
    type: row.type_id
  })