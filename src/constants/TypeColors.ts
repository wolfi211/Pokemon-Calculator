export interface TypeTheme {
  border: string;
  bg: string;
  ring: string;
  text: string;
  dropdown: string;
  optionPointed: string;
}

export const TYPE_COLORS: Record<string, TypeTheme> = {
  normal: {
    border: 'border-slate-400',
    bg: 'bg-slate-400',
    ring: 'ring-slate-400/30',
    text: 'text-slate-900',
    dropdown: 'border-slate-500',
    optionPointed: 'bg-slate-100'
  },
  fire: {
    border: 'border-orange-500',
    bg: 'bg-orange-500',
    ring: 'ring-orange-500/30',
    text: 'text-white',
    dropdown: 'border-orange-600',
    optionPointed: 'bg-orange-50'
  },
  water: {
    border: 'border-blue-500',
    bg: 'bg-blue-500',
    ring: 'ring-blue-500/30',
    text: 'text-white',
    dropdown: 'border-blue-600',
    optionPointed: 'bg-blue-50'
  },
  // ... Add the rest (Grass, Electric, etc.)
};

export const getTypeTheme = (typeName?: string): TypeTheme => {
  return TYPE_COLORS[typeName?.toLowerCase() || ''] || {
    border: 'border-emerald-500',
    bg: 'bg-emerald-500',
    ring: 'ring-emerald-500/30',
    text: 'text-white',
    dropdown: 'border-emerald-600',
    optionPointed: 'bg-emerald-50'
  };
};