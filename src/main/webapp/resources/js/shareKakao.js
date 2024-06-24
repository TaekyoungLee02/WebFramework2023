Kakao.init('3502a235decf6d0a408a0290695179c3');

function shareKakao(index)
{
    Kakao.Share.sendDefault({
        objectType: 'feed',
        content: {
          title: 'AI가 그려주는 오늘의 날씨',
          description: '나만의 AI 화가와 대화를 나누며 오늘의 날씨를 그림으로 받아봐요',
          imageUrl: imageLinks['img_' + index],
          link: {
            mobileWebUrl: 'https://developers.kakao.com',
            webUrl: 'https://developers.kakao.com',
          },
        },
        buttons: [
          {
            title: '웹으로 이동',
            link: {
              mobileWebUrl: 'https://developers.kakao.com',
              webUrl: 'https://developers.kakao.com',
            },
          }
        ],
    });
}