import type { Type } from "./type";

export interface Move {
  id: number;
  name: string;
  type: Type | undefined;
}