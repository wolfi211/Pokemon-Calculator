import type { Move } from "./move";
import type { StatBlock } from "./statBlock";
import type { Type } from "./type";

export interface Pokemon {
  id: number;
  name: string;
  types: Type[];
  stats: StatBlock;
  moves: Move[];
  spriteUrl: string;
}