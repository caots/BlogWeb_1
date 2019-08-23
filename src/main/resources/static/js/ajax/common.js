function errMess(jqXHR, textStatus, errorThrown) {
    console.log('jqXHR:');
    console.log(jqXHR);
    console.log('textStatus:');
    console.log(textStatus);
    console.log('errorThrown:');
    console.log(errorThrown);
}

const tokenHeader_value = sessionStorage.getItem("token");
console.log(tokenHeader_value);

const value_token_public = '1df0e68d684175afa5ae2c3d1543fa0e';


//format money
function formatNumber(nStr, decSeperate, groupSeperate) {
    nStr += '';
    x = nStr.split(decSeperate);
    x1 = x[0];
    x2 = x.length > 1 ? '.' + x[1] : '';
    var rgx = /(\d+)(\d{3})/;
    while (rgx.test(x1)) {
        x1 = x1.replace(rgx, '$1' + groupSeperate + '$2');
    }
    return x1 + x2;
}

// Replaces all instances of the given substring.
String.prototype.replaceAllll = function (
    strTarget, // The substring you want to replace
    strSubString // The string you want to replace in.
) {
    var strText = this;
    var intIndexOfMatch = strText.indexOf(strTarget);

// Keep looping while an instance of the target string
// still exists in the string.
    while (intIndexOfMatch !== -1) {
// Relace out the current instance.
        strText = strText.replace(strTarget, strSubString);

// Get the index of any next matching substring.
        intIndexOfMatch = strText.indexOf(strTarget);
    }

// Return the updated string with ALL the target strings
// replaced out with the new substring.
    return (strText);
};

var uploadFile = async (file) => {
    let data;
    await $.ajax({
        type: "POST",
        url: "https://api.imgbb.com/1/upload?key=96576401881b8a0f10647bf04074362f",
        data: file,
        async: false,
        cache: false,
        contentType: false,
        enctype: 'multipart/form-data',
        processData: false,
        success: function (result) {
            data = result;
        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
        }
    });
    return data;
};