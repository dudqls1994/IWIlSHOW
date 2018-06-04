package media.co.kr.dto;
public class VolDto {
   private int idx;
   private String name;
   private String writer;
   private String contents;
   private String date;
   private String filename;
   private String path;
   
   public int getIdx() {
      return idx;
   }
   public void setIdx(int idx) {
      this.idx = idx;
   }
   public String getName() {
      return name;
   }
   public void setName(String name) {
      this.name = name;
   }
   public String getWriter() {
      return writer;
   }
   public void setWriter(String writer) {
      this.writer = writer;
   }
   public String getContents() {
      return contents;
   }
   public void setContents(String contents) {
      this.contents = contents;
   }
   public String getDate() {
      return date;
   }
   public void setDate(String date) {
      this.date = date;
   }
   public void setFilename(String filename) {
      this.filename=filename;
   }
   public String getFilename() {
      return filename;
   }
   public void setPath(String path) {
      this.path=path;
   }
   public String getPath() {
      return path;
   }

   public VolDto(String name, String writer, String contents, String date, String filename, String path) {
      super();
      this.name = name;
      this.writer = writer;
      this.contents = contents;
      this.date = date;
      this.filename = filename;
      this.path = path;
   }
   public VolDto(String name, String writer, String contents, String date, String filename) {
      super();
      this.name = name;
      this.writer = writer;
      this.contents = contents;
      this.date = date;
      this.filename = filename;
   }
   public VolDto( String name, String writer, String contents, String date) {
      super();
   
      this.name = name;
      this.writer = writer;
      this.contents = contents;
      this.date = date;
   }
   public VolDto() {
      super();
   }

   

}