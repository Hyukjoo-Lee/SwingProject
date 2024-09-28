
# Swing GUI를 활용한 관리자 시스템

이 프로젝트는 Swing GUI와 JDBC를 사용한 데이터베이스 연동을 학습하기 위해 개발된 관리자 시스템입니다. 주요 목표는 Oracle과 Java 프로젝트를 연결하여 직원 정보에 대한 CRUD(생성, 조회, 수정, 삭제) 작업을 수행하는 것입니다.

## 팀 구성원
이 프로젝트는 총 5명의 팀원이 개발하였습니다:

- 이혁주
- 정은총
- 허민경
- 김규원
- 김영민

## 프로젝트 개요
이 관리자 시스템은 Java의 Swing 인터페이스와 JDBC를 사용하여 Oracle 데이터베이스와 연결됩니다. 시스템은 관리자가 직원 데이터를 효율적으로 관리할 수 있도록 다음과 같은 주요 기능을 제공합니다.

### 구현된 기능
1. **로그인**: 관리자가 시스템에 로그인할 수 있는 기능.
2. **회원가입**: 새로운 관리자를 시스템에 등록할 수 있는 기능.
3. **직원 추가/조회/삭제/레벨 변경**: 직원 정보를 추가, 조회, 삭제하거나 레벨을 변경할 수 있는 기능.

## 개발 환경
- **데이터베이스**: Oracle 11g
- **JDBC 드라이버**: ojdbc6.jar
- **Java 버전**: JDK 17
- **개발 도구**: Spring Tool Suite (STS) 4.25.0, SQL Developer

## 설치 및 실행 방법
1. `ojdbc6.jar` 라이브러리를 프로젝트에 import하여 JDBC 드라이버를 설정합니다.
2. STS에서 프로젝트를 열고 실행합니다.

## 사용 예시
1. 프로그램 실행 후 로그인 창이 표시됩니다.
2. 회원가입을 통해 새로운 관리자로 등록할 수 있습니다.
3. 로그인 후 직원 정보 관리 페이지에서 직원 추가, 조회, 수정, 삭제 기능을 사용할 수 있습니다.

---

이 프로젝트를 통해 Java와 Oracle 데이터베이스 간의 연동 방법을 학습하고, CRUD operation를 구현해보며 기본적인 JDBC 개념을 학습할 수 있었습니다.

---

#### **이 프로젝트에서의 깃 사용 시나리오**

현재 프로젝트에서 팀원들은 각자 브랜치를 만들어 작업하고, 최신 프로젝트 변경 사항을 `main` 브랜치에서 가져와 자신의 브랜치에 반영한 후 변경 사항을 푸시합니다. 팀 리더는 각 브랜치의 변경 사항을 검토하고 필요한 경우 반영합니다.

**기본적인 워크플로우**:

1. **자신의 브랜치로 전환**: 작업을 시작하기 전에 자신의 브랜치로 전환합니다.
   ```bash
   git checkout <your-branch>
   ```

2. **`main` 브랜치에서 최신 변경사항 가져오기**: 작업을 시작하기 전에 `main` 브랜치에서 최신 변경 사항을 가져와 자신의 브랜치에 반영합니다.
   ```bash
   git pull origin main
   ```

   이 단계는 최신 코드와 충돌 없이 작업을 지속하기 위해 중요합니다.

3. **작업 후 변경사항 푸시**: 작업을 완료한 후 변경 사항을 자신의 브랜치에 커밋하고 원격 저장소에 푸시합니다.
   ```bash
   git add .
   git commit -m "작업 내용 설명"
   git push origin <your-branch>
   ```

4. **팀 리더의 역할**: 팀 리더는 각 팀원의 브랜치에서 변경 사항을 검토하고 `main` 브랜치에 반영할지 결정합니다.

## 깃 기본 명령어

### 1. Git 설정 관련 명령어
- `git config --global user.name "사용자이름"`: Git에 사용자 이름을 설정합니다.
- `git config --global user.email "이메일주소"`: Git에 사용자 이메일을 설정합니다.

### 2. 저장소 초기화
- `git init`: 현재 디렉터리를 Git 저장소로 초기화합니다.

### 3. 파일 상태 확인
- `git status`: 파일의 현재 상태를 확인합니다.

### 4. 파일 추가 및 커밋
- `git add <파일명>`: 특정 파일을 추가합니다.
- `git add .`: 모든 변경된 파일을 추가합니다.
- `git commit -m "커밋 메시지"`: 파일을 커밋합니다.

### 5. 브랜치 관련 명령어
- `git branch`: 현재 브랜치 목록을 확인합니다.
- `git branch <브랜치명>`: 새로운 브랜치를 생성합니다.
- `git checkout <브랜치명>`: 특정 브랜치로 전환합니다.
- `git checkout -b <브랜치명>`: 새로운 브랜치를 생성하고 전환합니다.

### 6. 원격 저장소 관련 명령어
- `git clone <저장소URL>`: 원격 저장소를 복제합니다.
- `git remote add origin <저장소URL>`: 원격 저장소를 추가합니다.
- `git remote -v`: 연결된 원격 저장소의 URL을 확인합니다.
- `git push origin <브랜치명>`: 변경 사항을 원격 저장소에 푸시합니다.

### 7. 로그 확인
- `git log`: 커밋 로그를 확인합니다.

다음은 `git pull` 명령어의 기본 사용법과 여러 브랜치를 사용하는 워크플로우에 대한 설명입니다.

### 8. 최신 변경사항 가져오기 

- `git pull origin main`:  `main` 브랜치에서 최신 변경 사항을 가져와 현재 작업 중인 브랜치에 병합합니다.