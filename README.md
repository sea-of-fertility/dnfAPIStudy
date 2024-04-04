# 프로젝트 소개
한국 온라인 PC게임 던전 앤 파이터의 open API를 활용한 프로젝트 입니다.

---

개발 환경: spring-boot 3.1 / asciidoctor1.5.8
- 기능
    - 캐릭터 검색시 이미지를 저장한다.
    - spring docs를 이용해서 api 문서를 제작했다.

# 고민 사항
Bean Validator을 통한 검증시 여러개의 유효하지 않은 데이터가 들어올 때 어떻게 할까?
유효하지 않은 데이터가 들어올 경우 아래 메소드를 통해 ErrorResponse를 반환 한다.
    
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse invalidRequestHandler(MethodArgumentNotValidException e) {

        ErrorResponse errorResponse = ErrorResponse.builder()
                .code("400")
                .message("잘못된 요청입니다.")
                .build();
        errorResponse.addValidation(e.getFieldErrors());
        return errorResponse;
    }

유효하지 않은 FildError를 list<FildError>를 통해 받고 fileName과 errorMessage만을 저장하는 ErrorDTO에 담는다.

 
    public record ErrorResponse(String code, String message, List<ErrorDTO> validation) {
        @Builder
        public ErrorResponse(String code, String message, List<ErrorDTO> validation) {
            this.code = code;
            this.message = message;
            this.validation = validation == null ? new ArrayList<>() : validation;
        }

        public void addValidation(List<FieldError> errors) {
            errors.stream().map(ErrorDTO::new).forEach(this.validation::add);
        }
    
        public void addValidation(FieldError errors) {
            this.validation.add(new ErrorDTO(errors));
        }

    }



ErrorResponse 클래스에 
