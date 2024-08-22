int printf(const char* format, ...);
int scanf(const char* format, ...);
int sprintf(char* str, const char* format, ...);
void* malloc(unsigned int size);

void print(char* s) { printf("%s", s); }

void println(char* s) { printf("%s\n", s); }

void printInt(int n) { printf("%d", n); }

void printlnInt(int n) { printf("%d\n", n); }

char* getString() {
  char* s = (char*) malloc(sizeof(char) * 256);
  scanf("%s", s);
  return s;
}

int getInt() {
  int n;
  scanf("%d", &n);
  return n;
}

char* toString(int i) {
  char* s;
  s = (char*) malloc(sizeof(char) * 12);
  sprintf(s, "%d", i);
  return s;
}

void* __alloc_single(int size, int length) {
  int* a = (int*) malloc(size * length + 4);
  a[0] = length;
  return a + 1;
}

// The implementation here is learned from Conless.

void* __alloc_multi(int size, int dim, int* params, int param_length) {
  if(dim == 1) return __alloc_single(size, *params);
  void* array = __alloc_single(sizeof(void*), *params);
  if(param_length == 1) return array;
  for(register int i = 0; i < *params; ++i) {
    ((void**)array)[i] = __alloc_multi(size, dim - 1, params + 1, param_length - 1);
  }
  return array;
}

void* __alloc(int size, int dim, int param_length, ...) {
  int* params = (int*) malloc(sizeof(int) * param_length);
  __builtin_va_list list;
  __builtin_va_start(list, param_length);
  for(register int i = 0; i < param_length; ++i) {
    params[i] = __builtin_va_arg(list, int);
  }
  __builtin_va_end(list);
  return __alloc_multi(size, dim, params, param_length);
}

int __array_size(void* array) { return ((int*)array)[-1]; }

int __string_length(char* s) {
  register int i = 0;
  while (s[i] != '\0') {
    i++;
  }
  return i;
}

char* __string_substring(char* s, int left, int right) {
  int len = right - left;
  char* result = (char*) malloc(sizeof(char) * (len + 1));
  for (register int i = 0; i < len; i++) {
    result[i] = s[left + i];
  }
  result[len] = '\0';
  return result;
}

int __string_parseInt(char* s) {
  int result = 0;
  for (register int i = (s[0] == '-'); s[i] != '\0'; ++i) {
    result = result * 10 + s[i] - '0';
  }
  if (s[0] == '-') {
    result = -result;
  }
  return result;
}

int __string_ord(char* s, int i) { return s[i]; }

int __string_compare(char* s1, char* s2) {
  register int i = 0;
  while (s1[i] != '\0' && s2[i] != '\0') {
    if (s1[i] != s2[i]) {
      return s1[i] - s2[i];
    }
    ++i;
  }
  return s1[i] - s2[i];
}

char* __string_concat(char* s1, char* s2) {
  int len1 = __string_length(s1);
  int len2 = __string_length(s2);
  char* result = (char*) malloc(sizeof(char) * (len1 + len2 + 1));
  for(register int i = 0; i < len1; ++i) {
    result[i] = s1[i];
  }
  for(register int i = 0; i < len2; ++i) {
    result[len1 + i] = s2[i];
  }
  result[len1 + len2] = '\0';
  return result;
}

void __string_copy(char** s1, char* s2) {
  int len = __string_length(s2);
  *s1 = (char*) malloc(sizeof(char) * (len + 1));
  for (register int i = 0; i < len; ++i) {
    (*s1)[i] = s2[i];
  }
  (*s1)[len] = '\0';
}