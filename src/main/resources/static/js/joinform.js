function readURL(input) {

    console.log("버튼클릭함1");

    if (input.files && input.files[0]) {

        var reader = new FileReader();

        reader.onload = function(e) {

            $('#cover').attr('src', e.target.result); //cover src로 붙여지고

            $('#fileName').val(input.files[0].name); //파일선택 form으로 파일명이 들어온다

        }

        reader.readAsDataURL(input.files[0]);

    }

}

$("#myFileUp").change(function() {

    readURL(this);

    console.log("이미지 바뀜?");

});