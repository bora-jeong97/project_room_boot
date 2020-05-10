<template>
  <div class="broker-information-container">
    <div class="broker-information-header-container">
      <div class="broker-information-profile-container">
        <div class="broker-information-profile-wrapper">
          <img v-if="brokerData&&brokerData.profile_image" :src="brokerData.profile_image" />
          <img v-else src="../assets/room1.jpg" />
        </div>
        <div class="broker-name">
          <span v-if="brokerData">{{brokerData.name}}</span>중개사사무소
        </div>
        <div class="broker-satisfaction">
          <svg width="18" height="18" viewBox="0 0 18 18">
            <g fill="none" fill-rule="evenodd">
              <path
                stroke="#3185F8"
                d="M15.644 7.638l-.537-.548c-.416-.433-.954-.656-1.211-.676h-2.032v-3.78c-.052-.38-.346-.634-.743-.634H9.894c-.456 0-.694.372-.753.608l-.65 2.455-2.798 2.152a.952.952 0 0 0-.83-.486H2.956A.95.95 0 0 0 2 7.674v7.377a.95.95 0 0 0 .955.945h1.947c.527 0 .92-.423.92-.945.768.63 1.51.945 2.223.945l4.94.004c.16 0 .953-.02 1.507-.609l.834-.862A2.58 2.58 0 0 0 16 12.804l-.002-4.428c0-.394-.197-.512-.354-.738z"
              />
              <path fill="#3185F8" d="M5.322 7.002h1v8.586h-1z" />
            </g>
          </svg>
          <span
            v-if="reviewLists && brokerData.review_list"
            class="broker-satisfaction-title"
          >{{reviewLists[0] ? reviewLists[0].best_count : 0}}명이 상담후 만족했습니다</span>
          <span v-else class="broker-satisfaction-title">0명이 상담후 만족했습니다</span>
        </div>

        <div class="broker-information-detail-container">
          <div class="broker-information-wrapper">
            <div class="broker-information">
              <div class="broker-information-item">공인중개사이름</div>
              <div class="broker-information-item1">
                <span v-if="brokerData">{{brokerData.name}}</span>중개사사무소
              </div>
            </div>
            <div class="broker-information">
              <div class="broker-information-item">대표자명</div>
              <div class="broker-information-item1">
                <span v-if="brokerData">{{brokerData.name}}</span>
              </div>
            </div>
            <div class="broker-information">
              <div class="broker-information-item">대표번호</div>
              <div class="broker-information-item1">
                <span v-if="brokerData">{{brokerData.phone_number}}</span>
              </div>
            </div>
          </div>
          <div class="broker-information-wrapper">
            <div class="broker-information">
              <div class="broker-information-item">사업자등록번호</div>
              <div class="broker-information-item1">0000-0000-0000</div>
            </div>
            <div class="broker-information">
              <div class="broker-information-item">구해방가입일</div>
              <div class="broker-information-item1">
                <span v-if="brokerData">{{brokerData.registered_at.split(" ")[0]}}</span>
              </div>
            </div>
            <div class="broker-information">
              <div class="broker-information-item">주소</div>
              <div class="broker-information-item1">
                <span v-if="brokerData">{{brokerData.address}}</span>
              </div>
            </div>
          </div>
        </div>

        <div class="broker-room-contaniner">
          <div class="broker-review-textarea-title broker-review-title-container1">
            이 부동산의 방
            <span>({{roomLists.length}})</span>
          </div>
          <!-- 값이 있을때만 props를 넘긴다 -->
          <KeepRoom v-if="roomLists !== '' " :roomLists="roomLists" />
        </div>
        <div class="broker-review-container">
          <div class="broker-review-textarea-title">
            부동산리뷰
            <span v-if="reviewLists">({{reviewLists.length}})</span>
          </div>
          <div v-if="loginData.user_id" class="broker-review-textarea-wrapper">
            <textarea
              v-model="content"
              class="broker-review-textarea"
              placeholder="해당 부동산의 전체적인 평가에 대해 상세하게 작성
(상세한 리뷰는 도움이 되지만, 무조건적인 비방글은 삼가해주세요!)"
            ></textarea>
          </div>
          <div v-if="loginData.user_id" class="broker-review-button-wrapper">
            <p class="broker-review-button-title">평가를 선택하세요</p>
            <div @click="controllerIcon('best')" class="broker-review-icon-wrapper">
              <svg v-if="best" width="24" height="24" viewBox="0 0 24 24">
                <g fill="#FFF" fill-rule="evenodd">
                  <path
                    stroke="#4383ff"
                    stroke-width="1.2"
                    d="M23 12c0 6.075-4.925 11-11 11S1 18.075 1 12 5.925 1 12 1s11 4.925 11 11z"
                  />
                  <path
                    fill="#4383ff"
                    d="M7.82 11.164c.192-.156 1.436-1.254 1.753-1.698a1.18 1.18 0 0 0 .227-.748C9.8 8.1 9.336 7.6 8.765 7.6c-.566 0-.945.637-.945.637S7.44 7.6 6.875 7.6c-.571 0-1.035.5-1.035 1.118 0 .264.044.49.227.748.317.444 1.561 1.542 1.753 1.698zM16.18 11.164c.192-.156 1.436-1.254 1.753-1.698a1.18 1.18 0 0 0 .227-.748c0-.618-.464-1.118-1.035-1.118-.566 0-.945.637-.945.637s-.38-.637-.945-.637c-.571 0-1.035.5-1.035 1.118 0 .264.044.49.227.748.317.444 1.561 1.542 1.753 1.698z"
                  />
                  <path
                    fill="#4383ff"
                    stroke="#4383ff"
                    stroke-linecap="round"
                    stroke-linejoin="round"
                    stroke-width="1.2"
                    d="M17.5 13.98c0 3.038-2.462 4.932-5.5 4.932s-5.5-1.894-5.5-4.932h11z"
                  />
                </g>
              </svg>
              <svg v-else width="24" height="24" viewBox="0 0 24 24">
                <g fill="none" fill-rule="evenodd">
                  <path
                    stroke="#666"
                    stroke-width="1.2"
                    d="M23 12c0 6.075-4.925 11-11 11S1 18.075 1 12 5.925 1 12 1s11 4.925 11 11z"
                  />
                  <path
                    fill="#666"
                    d="M7.82 11.164c.192-.156 1.436-1.254 1.753-1.698a1.18 1.18 0 0 0 .227-.748C9.8 8.1 9.336 7.6 8.765 7.6c-.566 0-.945.637-.945.637S7.44 7.6 6.875 7.6c-.571 0-1.035.5-1.035 1.118 0 .264.044.49.227.748.317.444 1.561 1.542 1.753 1.698zM16.18 11.164c.192-.156 1.436-1.254 1.753-1.698a1.18 1.18 0 0 0 .227-.748c0-.618-.464-1.118-1.035-1.118-.566 0-.945.637-.945.637s-.38-.637-.945-.637c-.571 0-1.035.5-1.035 1.118 0 .264.044.49.227.748.317.444 1.561 1.542 1.753 1.698z"
                  />
                  <path
                    stroke="#666"
                    stroke-linecap="round"
                    stroke-linejoin="round"
                    stroke-width="1.2"
                    d="M17.5 13.98c0 3.038-2.462 4.932-5.5 4.932s-5.5-1.894-5.5-4.932h11z"
                  />
                </g>
              </svg>
              <span>최고에요</span>
            </div>
            <div
              @click="controllerIcon('good')"
              class="broker-review-icon-wrapper broker-review-icon2"
            >
              <svg v-if="good" width="24" height="24" viewBox="0 0 24 24">
                <g fill="#FFF" fill-rule="evenodd">
                  <g stroke="#48d0ae" stroke-width="2">
                    <path
                      d="M23 12c0 6.075-4.925 11-11 11S1 18.075 1 12 5.925 1 12 1s11 4.925 11 11z"
                    />
                    <path
                      stroke-linecap="round"
                      stroke-linejoin="round"
                      d="M17.5 13.98c0 3.038-2.462 4.932-5.5 4.932s-5.5-1.894-5.5-4.932h11z"
                      fill="#48d0ae"
                    />
                  </g>
                  <g fill="#48d0ae" transform="translate(6.5 8.7)">
                    <circle cx="1.32" cy="1.32" r="1.32" />
                    <circle cx="9.68" cy="1.32" r="1.32" />
                  </g>
                </g>
              </svg>
              <svg v-else width="24" height="24" viewBox="0 0 24 24">
                <g fill="none" fill-rule="evenodd">
                  <g stroke="#666" stroke-width="1.2">
                    <path
                      d="M23 12c0 6.075-4.925 11-11 11S1 18.075 1 12 5.925 1 12 1s11 4.925 11 11z"
                    />
                    <path
                      stroke-linecap="round"
                      stroke-linejoin="round"
                      d="M17.5 13.98c0 3.038-2.462 4.932-5.5 4.932s-5.5-1.894-5.5-4.932h11z"
                    />
                  </g>
                  <g fill="#666" transform="translate(6.5 8.7)">
                    <circle cx="1.32" cy="1.32" r="1.32" />
                    <circle cx="9.68" cy="1.32" r="1.32" />
                  </g>
                </g>
              </svg>
              <span>좋아요</span>
            </div>
            <div
              @click="controllerIcon('soso')"
              class="broker-review-icon-wrapper broker-review-icon3"
            >
              <svg v-if="soso" width="24" height="24" viewBox="0 0 24 24">
                <g fill="none" fill-rule="evenodd">
                  <path
                    stroke="#ffbb31"
                    stroke-width="2"
                    d="M23 12c0 6.075-4.925 11-11 11S1 18.075 1 12 5.925 1 12 1s11 4.925 11 11z"
                  />
                  <g fill="#ffbb31" transform="translate(6.5 8.7)">
                    <rect width="8.8" height="1.65" x="1.1" y="7.7" rx=".825" />
                    <circle cx="1.32" cy="1.32" r="1.32" />
                    <circle cx="9.68" cy="1.32" r="1.32" />
                  </g>
                </g>
              </svg>
              <svg v-else width="24" height="24" viewBox="0 0 24 24">
                <g fill="none" fill-rule="evenodd">
                  <path
                    stroke="#666"
                    stroke-width="1.2"
                    d="M23 12c0 6.075-4.925 11-11 11S1 18.075 1 12 5.925 1 12 1s11 4.925 11 11z"
                  />
                  <g fill="#666" transform="translate(6.5 8.7)">
                    <rect width="8.8" height="1.65" x="1.1" y="7.7" rx=".825" />
                    <circle cx="1.32" cy="1.32" r="1.32" />
                    <circle cx="9.68" cy="1.32" r="1.32" />
                  </g>
                </g>
              </svg>
              <span>괜찮아요</span>
            </div>
            <div
              class="broker-review-icon-wrapper broker-review-icon4"
              @click="controllerIcon('nope')"
            >
              <svg v-if="nope" width="24" height="24" viewBox="0 0 24 24">
                <g fill="none" fill-rule="evenodd">
                  <path
                    stroke="#ee5464"
                    stroke-width="2"
                    d="M23 12c0 6.075-4.925 11-11 11S1 18.075 1 12 5.925 1 12 1s11 4.925 11 11z"
                  />
                  <g fill="#ee5464" transform="translate(6.5 8.7)">
                    <circle cx="1.32" cy="1.32" r="1.32" />
                    <circle cx="9.68" cy="1.32" r="1.32" />
                  </g>
                  <path
                    stroke="#ee5464"
                    stroke-linecap="round"
                    stroke-linejoin="round"
                    stroke-width="1.4"
                    d="M16.62 17.23c-.931-1.805-2.471-2.708-4.62-2.708s-3.689.903-4.62 2.708"
                  />
                </g>
              </svg>
              <svg v-else width="24" height="24" viewBox="0 0 24 24">
                <g fill="none" fill-rule="evenodd">
                  <path
                    stroke="#666"
                    stroke-width="1.2"
                    d="M23 12c0 6.075-4.925 11-11 11S1 18.075 1 12 5.925 1 12 1s11 4.925 11 11z"
                  />
                  <g fill="#666" transform="translate(6.5 8.7)">
                    <circle cx="1.32" cy="1.32" r="1.32" />
                    <circle cx="9.68" cy="1.32" r="1.32" />
                  </g>
                  <path
                    stroke="#666"
                    stroke-linecap="round"
                    stroke-linejoin="round"
                    stroke-width="1.4"
                    d="M16.62 17.23c-.931-1.805-2.471-2.708-4.62-2.708s-3.689.903-4.62 2.708"
                  />
                </g>
              </svg>
              <span>글쎄요</span>
            </div>
            <button class="review-write-button" @click="writeReview">등록</button>
          </div>

          <div class="broker-icon-count-wrapper">
            <div class="broker-icon-count broker-icon-count1">
              <svg width="24" height="24" viewBox="0 0 24 24">
                <g fill="#FFF" fill-rule="evenodd">
                  <path
                    stroke="#4383ff"
                    stroke-width="1.2"
                    d="M23 12c0 6.075-4.925 11-11 11S1 18.075 1 12 5.925 1 12 1s11 4.925 11 11z"
                  />
                  <path
                    fill="#4383ff"
                    d="M7.82 11.164c.192-.156 1.436-1.254 1.753-1.698a1.18 1.18 0 0 0 .227-.748C9.8 8.1 9.336 7.6 8.765 7.6c-.566 0-.945.637-.945.637S7.44 7.6 6.875 7.6c-.571 0-1.035.5-1.035 1.118 0 .264.044.49.227.748.317.444 1.561 1.542 1.753 1.698zM16.18 11.164c.192-.156 1.436-1.254 1.753-1.698a1.18 1.18 0 0 0 .227-.748c0-.618-.464-1.118-1.035-1.118-.566 0-.945.637-.945.637s-.38-.637-.945-.637c-.571 0-1.035.5-1.035 1.118 0 .264.044.49.227.748.317.444 1.561 1.542 1.753 1.698z"
                  />
                  <path
                    fill="#4383ff"
                    stroke="#4383ff"
                    stroke-linecap="round"
                    stroke-linejoin="round"
                    stroke-width="1.2"
                    d="M17.5 13.98c0 3.038-2.462 4.932-5.5 4.932s-5.5-1.894-5.5-4.932h11z"
                  />
                </g>
              </svg>
              <p class="broker-icon-sub">최고에요</p>
              <p class="broker-icon-count-number">
                <span
                  v-if="reviewLists && brokerData.review_list"
                >{{reviewLists[0] ? reviewLists[0].best_count : 0}}</span>
                <span v-else>0</span>
              </p>
            </div>
            <div class="broker-icon-count broker-icon-count2">
              <svg width="24" height="24" viewBox="0 0 24 24">
                <g fill="#FFF" fill-rule="evenodd">
                  <g stroke="#48d0ae" stroke-width="1.2">
                    <path
                      d="M23 12c0 6.075-4.925 11-11 11S1 18.075 1 12 5.925 1 12 1s11 4.925 11 11z"
                    />
                    <path
                      fill="#48d0ae"
                      stroke-linecap="round"
                      stroke-linejoin="round"
                      d="M17.5 13.98c0 3.038-2.462 4.932-5.5 4.932s-5.5-1.894-5.5-4.932h11z"
                    />
                  </g>
                  <g fill="#48d0ae" transform="translate(6.5 8.7)">
                    <circle cx="1.32" cy="1.32" r="1.32" />
                    <circle cx="9.68" cy="1.32" r="1.32" />
                  </g>
                </g>
              </svg>
              <p class="broker-icon-sub">좋아요</p>
              <p class="broker-icon-count-number">
                <span v-if="reviewLists">{{reviewLists[0] ? reviewLists[0].good_count : 0}}</span>
                <span v-else>0</span>
              </p>
            </div>
            <div class="broker-icon-count broker-icon-count3">
              <svg width="24" height="24" viewBox="0 0 24 24">
                <g fill="#FFF" fill-rule="evenodd">
                  <path
                    stroke="#ffbb31"
                    stroke-width="1.2"
                    d="M23 12c0 6.075-4.925 11-11 11S1 18.075 1 12 5.925 1 12 1s11 4.925 11 11z"
                  />
                  <g fill="#ffbb31" transform="translate(6.5 8.7)">
                    <rect width="8.8" height="1.65" x="1.1" y="7.7" rx=".825" />
                    <circle cx="1.32" cy="1.32" r="1.32" />
                    <circle cx="9.68" cy="1.32" r="1.32" />
                  </g>
                </g>
              </svg>
              <p class="broker-icon-sub">괜찮아요</p>
              <p class="broker-icon-count-number">
                <span v-if="reviewLists">{{reviewLists[0] ? reviewLists[0].soso_count : 0}}</span>
                <span v-else>0</span>
              </p>
            </div>
            <div class="broker-icon-count broker-icon-count4">
              <svg width="24" height="24" viewBox="0 0 24 24">
                <g fill="#FFF" fill-rule="evenodd">
                  <path
                    stroke="#ee5464"
                    stroke-width="1.2"
                    d="M23 12c0 6.075-4.925 11-11 11S1 18.075 1 12 5.925 1 12 1s11 4.925 11 11z"
                  />
                  <g fill="#ee5464" transform="translate(6.5 8.7)">
                    <circle cx="1.32" cy="1.32" r="1.32" />
                    <circle cx="9.68" cy="1.32" r="1.32" />
                  </g>
                  <path
                    stroke="#ee5464"
                    stroke-linecap="round"
                    stroke-linejoin="round"
                    stroke-width="1.4"
                    d="M16.62 17.23c-.931-1.805-2.471-2.708-4.62-2.708s-3.689.903-4.62 2.708"
                  />
                </g>
              </svg>
              <p class="broker-icon-sub">별로에요</p>
              <p class="broker-icon-count-number">
                <span v-if="reviewLists">{{reviewLists[0] ? reviewLists[0].nope_count : 0}}</span>
                <span v-else>0</span>
              </p>
            </div>
          </div>
          <div
            v-for="(reviewList,index) in reviewLists"
            :key="index"
            class="broker-review-list-container"
          >
            <div v-if="reviewList.depth === 0" class="broker-review-list-wrapper">
              <div v-if="reviewList.status === 'best'" class="broker-review-list-icon-wrapper">
                <div>
                  <svg width="44" height="44" viewBox="0 0 44 44">
                    <g fill="none" fill-rule="evenodd">
                      <circle cx="22" cy="22" r="22" fill="#4383FF" />
                      <path
                        fill="#FFF"
                        d="M38 22c0 8.836-7.164 16-16 16S6 30.836 6 22 13.164 6 22 6s16 7.164 16 16zm-8 3H14c0 4.311 3.582 7 8 7s8-2.689 8-7zm-13.814-3.63c.277-.226 2.077-1.814 2.535-2.457.264-.371.328-.699.328-1.082 0-.893-.67-1.616-1.497-1.616-.818 0-1.366.922-1.366.922s-.55-.922-1.367-.922c-.827 0-1.497.723-1.497 1.616 0 .383.064.71.329 1.082.458.643 2.257 2.23 2.535 2.456zm11.57 0c.278-.226 2.078-1.814 2.535-2.457.265-.371.33-.699.33-1.082 0-.893-.671-1.616-1.498-1.616-.818 0-1.367.922-1.367.922s-.548-.922-1.366-.922c-.827 0-1.497.723-1.497 1.616 0 .383.064.71.329 1.082.457.643 2.257 2.23 2.534 2.456z"
                      />
                    </g>
                  </svg>
                </div>
                <div class="broker-review-list-icon-text">최고에요</div>
              </div>
              <div v-if="reviewList.status === 'good'" class="broker-review-list-icon-wrapper">
                <div>
                  <svg width="44" height="44" viewBox="0 0 44 44">
                    <g fill="none" fill-rule="evenodd">
                      <circle cx="22" cy="22" r="22" fill="#48D0AE" />
                      <path
                        fill="#FFF"
                        d="M38 22c0 8.836-7.164 16-16 16S6 30.836 6 22 13.164 6 22 6s16 7.164 16 16zm-22-1a2 2 0 1 0 0-4 2 2 0 0 0 0 4zm12 0a2 2 0 1 0 0-4 2 2 0 0 0 0 4zm2 4H14c0 4.311 3.582 7 8 7s8-2.689 8-7z"
                      />
                    </g>
                  </svg>
                </div>
                <div class="broker-review-list-icon-text">좋아요</div>
              </div>

              <div v-if="reviewList.status === 'soso'" class="broker-review-list-icon-wrapper">
                <div>
                  <svg width="44" height="44" viewBox="0 0 44 44">
                    <g fill="none" fill-rule="evenodd">
                      <circle cx="22" cy="22" r="22" fill="#FFBB31" />
                      <path
                        fill="#FFF"
                        d="M38 22c0 8.836-7.164 16-16 16S6 30.836 6 22 13.164 6 22 6s16 7.164 16 16zm-22.25 6a.75.75 0 0 0-.75.75v.5c0 .414.336.75.75.75h12.5a.75.75 0 0 0 .75-.75v-.5a.75.75 0 0 0-.75-.75h-12.5zm.25-7a2 2 0 1 0 0-4 2 2 0 0 0 0 4zm12 0a2 2 0 1 0 0-4 2 2 0 0 0 0 4z"
                      />
                    </g>
                  </svg>
                </div>
                <div class="broker-review-list-icon-text">괜찮아요</div>
              </div>

              <div v-if="reviewList.status === 'nope'" class="broker-review-list-icon-wrapper">
                <div>
                  <svg width="44" height="44" viewBox="0 0 44 44">
                    <g fill="none" fill-rule="evenodd">
                      <circle cx="22" cy="22" r="22" fill="#EE5464" />
                      <path
                        fill="#FFF"
                        d="M38 22c0 8.836-7.164 16-16 16S6 30.836 6 22 13.164 6 22 6s16 7.164 16 16zm-22-1a2 2 0 1 0 0-4 2 2 0 0 0 0 4zm12 0a2 2 0 1 0 0-4 2 2 0 0 0 0 4zm1.873 8.413C28.29 26.496 25.61 25 22 25c-3.61 0-6.29 1.496-7.873 4.413-.289.534-.069 1.19.492 1.466.561.275 1.25.066 1.54-.468 1.182-2.182 3.072-3.236 5.841-3.236s4.659 1.054 5.841 3.236c.29.534.979.743 1.54.468.56-.276.78-.932.492-1.466z"
                      />
                    </g>
                  </svg>
                </div>
                <div class="broker-review-list-icon-text">글쎄요</div>
              </div>

              <div class="broker-review-list-information-wrapper">
                <div class="broker-review-list-profile-wrapper">
                  <div class="broker-reivew-profile-image-wrapper">
                    <img
                      v-if="reviewList.user_information"
                      :src="reviewList.user_information.profile_image"
                    />
                    <img v-else src="../assets/bed.png" />
                    <!-- {{}} -->
                    <!-- <img :src="reviewList.user_information.profile_image" /> -->
                  </div>
                  <div
                    v-if="reviewList.user_information"
                    class="broker-review-profile-name"
                  >{{reviewList.user_information.name}}</div>
                  <div class="broker-review-profile-register">{{reviewList.created_at}}</div>
                </div>
                <div
                  class="broker-review-content"
                  v-if="reviewList.del === 0"
                >{{reviewList.content}}</div>
                <div v-else class="broker-review-content">삭제 된 댓글입니다.</div>
                <div class="broker-review-re-textarea-button-wrapper">
                  <button
                    v-if="brokerData.broker_id === loginData.broker_id"
                    class="broker-review-re-textarea-button"
                    @click="replyModalOpen(reviewList.id)"
                  >답글 작성하기</button>

                  <button
                    v-if="reviewList.user_information !== null && reviewList.user_information.user_id === loginData.user_id && reviewList.del === 0"
                    class="broker-review-re-textarea-button re-textarea-update"
                    @click="updateModalOpen(reviewList.id, reviewList.content)"
                  >수정하기</button>
                  <button
                    v-if="reviewList.user_information !== null && reviewList.user_information.user_id === loginData.user_id && reviewList.del === 0"
                    class="broker-review-re-textarea-button re-textarea-delete"
                    @click="deleteReply(reviewList.id)"
                  >삭제하기</button>
                </div>
              </div>
            </div>

            <!-- 답글용  -->
            <div v-else class="broker-review-list-wrapper broker-review-re-write-wrapper">
              <div class="broker-review-list-information-wrapper">
                <div class="broker-review-list-profile-wrapper">
                  <div class="re-write-arrow"></div>
                  <div class="broker-reivew-profile-image-wrapper">
                    <img :src="brokerData.profile_image" />
                  </div>
                  <div class="broker-review-profile-name">{{brokerData.name}}</div>
                  <div class="broker-review-profile-register">{{reviewList.created_at}}</div>
                </div>
                <div
                  class="broker-review-content"
                  v-if="reviewList.del === 0"
                >{{reviewList.content}}</div>
                <div v-else class="broker-review-content">삭제 된 댓글입니다.</div>
                <div
                  v-if="brokerData.broker_id === loginData.broker_id && reviewList.del === 0"
                  class="broker-review-re-textarea-button-wrapper"
                >
                  <button
                    class="broker-review-re-textarea-button re-textarea-update"
                    @click="updateModalOpen(reviewList.id, reviewList.content)"
                  >수정하기</button>
                  <button
                    class="broker-review-re-textarea-button re-textarea-delete"
                    @click="deleteReply(reviewList.id)"
                  >삭제하기</button>
                </div>
              </div>
            </div>
          </div>
        </div>

        <ModalQna v-if="replyModalShow">
          <div slot="header">
            <div class="reply-modal-header-wrapper">
              <div class="reply-modal-header-title">답글 작성하기</div>
              <div class="reply-modal-close-button-wrapper" @click="closeButton">
                <img src="../assets/close.png" />
              </div>
            </div>
          </div>
          <div slot="body">
            <div class="reply-modal-body-wrapper">
              <textarea class="reply-modal-textarea" v-model="replyContent"></textarea>
            </div>
          </div>
          <div slot="footer">
            <div class="reply-modal-button-wrapper">
              <div class="reply-modal-button" @click="replyWrite">답글작성하기</div>
            </div>
          </div>
        </ModalQna>

        <ModalQna v-if="updateModalShow">
          <div slot="header">
            <div class="reply-modal-header-wrapper">
              <div class="reply-modal-header-title">수정하기</div>
              <div class="reply-modal-close-button-wrapper" @click="closeUpdateModal">
                <img src="../assets/close.png" />
              </div>
            </div>
          </div>
          <div slot="body">
            <div class="reply-modal-body-wrapper">
              <textarea class="reply-modal-textarea" v-model="updateContent"></textarea>
            </div>
          </div>
          <div slot="footer">
            <div class="reply-modal-button-wrapper">
              <div class="reply-modal-button" @click="updateReview">수정하기</div>
            </div>
          </div>
        </ModalQna>
      </div>
    </div>
  </div>
</template>

<script>
import { request, requestParams } from "../util/axios";
import KeepRoom from "../components/banner/KeepRoom";
import ModalQna from "../components/ModalQna";
import { mapState } from "vuex";
export default {
  components: {
    KeepRoom,
    ModalQna
  },
  data() {
    return {
      brokerData: "",
      roomLists: "",
      best: true,
      good: false,
      soso: false,
      nope: false,
      content: "",
      reviewLists: "",
      replyModalShow: false,
      replyDetail: "",
      replyId: "", //reply저장 임시 변수
      replyContent: "",
      updateModalShow: false,
      updateId: "", //update를 위한 임시 변수
      updateContent: ""
    };
  },
  mounted() {
    console.log(this.$route.params.brokerId);
    this.getBrokerInformation();
  },
  computed: {
    ...mapState(["loginData"])
  },
  methods: {
    getBrokerInformation() {
      let params = new URLSearchParams();
      params.append("id", this.$route.params.brokerId);
      params.append("start", 6);
      request("post", "broker/getInformation", params).then(res => {
        console.log(res);
        this.brokerData = res;
        console.log(res.room_list);
        this.roomLists = res.room_list;

        this.reviewLists = res.review_list;
        console.log(this.reviewLists);
      });
    },
    controllerIcon(status) {
      console.log(status);
      this.falseIcon();
      if (status === "best") {
        this.best = true;
      } else if (status === "good") {
        this.good = true;
      } else if (status === "soso") {
        this.soso = true;
      } else if (status === "nope") {
        this.nope = true;
      }
    },
    falseIcon() {
      this.best = false;
      this.good = false;
      this.soso = false;
      this.nope = false;
    },
    writeReview() {
      // console.log(this.best);
      // console.log(this.good);
      // console.log(this.soso);
      // console.log(this.nope);
      let imgStatus = "";
      if (this.best) imgStatus = "best";
      else if (this.good) imgStatus = "good";
      else if (this.soso) imgStatus = "soso";
      else if (this.nope) imgStatus = "nope";
      console.log(imgStatus);
      requestParams("get", "review/write", {
        content: this.content, // 내용
        userId: this.loginData.user_id, //리뷰를 쓰는 유저의 아이디
        brokerReviewId: this.brokerData.id, //리뷰를 쓰는 대상의 아이디
        status: imgStatus
        // brokerId: this.brokerId,
        // auth: this.auth,
      }).then(res => {
        console.log(res);
        this.getBrokerInformation();
      });
    },
    replyModalOpen(id) {
      this.replyModalShow = true;
      console.log(id);
      // this.replyDetail
      this.replyId = id;
    },
    closeButton() {
      this.replyModalShow = false;
    },
    replyWrite() {
      console.log(this.replyId);
      requestParams("get", "review/replyInsert", {
        id: this.replyId,
        content: this.replyContent,
        brokerId: this.loginData.broker_id,
        brokerReviewId: this.loginData.id
      }).then(res => {
        console.log(res);
        this.getBrokerInformation();
        this.replyModalShow = false;
      });
    },
    updateModalOpen(id, content) {
      console.log(id);
      console.log(content);
      this.updateId = id;
      this.updateContent = content;
      this.updateModalShow = true;
    },
    updateUserReview(id) {
      console.log(id);
    },
    updateReview() {
      console.log(this.updateId);
      requestParams("get", "review/update", {
        id: this.updateId,
        content: this.updateContent
      }).then(res => {
        this.getBrokerInformation();
        this.closeUpdateModal();
      });
    },
    deleteReply(id) {
      console.log(id);
      let confirmDelete = confirm("해당 댓글을 삭제하시겠습니까?");
      if (confirmDelete) {
        requestParams("get", "review/delete", {
          id: id
        }).then(res => {
          this.getBrokerInformation();
          this.closeUpdateModal();
        });
      }
    },

    closeUpdateModal() {
      this.updateModalShow = false;
    }
  }
};
</script>

<style>
.broker-information-container {
  color: #222222;
}
.broker-information-profile-container {
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
}
.broker-information-profile-wrapper {
  width: 100px;
  height: 100px;
  margin: 30px 0 10px 0;
}
.broker-review-title-container1 {
  margin-top: 70px;
  padding-bottom: 40px;
}
.broker-information-profile-wrapper > img {
  width: 100%;
  height: 100%;
  border-radius: 50%;
}
.broker-name {
  font-size: 27px;
  margin-top: 10px;
}
.broker-satisfaction {
  margin-top: 20px;
  font-size: 17px;
  color: #3185f8;
  display: flex;
  align-items: center;
}

.broker-satisfaction-title {
  margin-left: 10px;
}

.broker-information-detail-container {
  width: 70%;
  margin-top: 50px;
  border-top: 3px solid #000;
}

.broker-information-wrapper {
  display: flex;
  /* justify-content: space-between; */
  border-bottom: 1px solid #dddddddd;
}
.broker-information-item::before {
  content: "·";
  float: left;
  color: rgb(34, 34, 34);
  margin-right: 7px;
}
.broker-information-item {
  color: #555;
  font-size: 12px;
  width: 200px;
}

.broker-information-item1 {
  margin-left: 50px;
  font-size: 13px;
  width: 150px;
}

.broker-information {
  display: flex;
  padding: 20px;
  align-items: center;
  width: 33%;
  height: 70px;
}
.broker-review-container {
  margin-top: 40px;
  margin-bottom: 140px;
}
.broker-room-contaniner {
  /* display: flex;
  justify-content: center; */
  padding-left: 30px;
}
.broker-review-textarea-wrapper {
  width: 850px;
  height: 178px;
  position: relative;
  margin: 0px auto;
}
.broker-review-textarea {
  box-sizing: border-box;
  -webkit-appearance: none;
  resize: none;
  width: 100%;
  height: 100%;
  color: rgb(136, 136, 136);
  font-size: 14px;
  line-height: 22px;
  background-color: rgb(255, 255, 255);
  position: relative;
  border-radius: 4px 4px 0px 0px;
  outline: none;
  padding: 20px;
  border-width: 1px;
  border-style: solid;
  border-color: rgb(170, 170, 170) rgb(170, 170, 170) rgb(226, 226, 226);
  border-image: initial;
}

.broker-review-button-wrapper {
  width: 850px;
  height: 51px;
  line-height: 51px;
  padding-left: 20px;
  border-bottom-right-radius: 4px;
  border-bottom-left-radius: 4px;
  position: relative;
  margin: 0px auto;
  border-width: 0px 1px 1px;
  border-style: solid solid solid;
  border-color: rgb(170, 170, 170) rgb(170, 170, 170) rgb(170, 170, 170);
  border-image: initial;
  border-top: 0px;
}

.broker-review-re-textarea {
  box-sizing: border-box;
  -webkit-appearance: none;
  resize: none;
  width: 500px;
  height: 70px;
  color: rgb(136, 136, 136);
  font-size: 14px;
  line-height: 22px;
  background-color: rgb(255, 255, 255);
  position: relative;
  border-radius: 4px 4px 0px 0px;
  outline: none;
  padding: 20px;
  border-width: 1px;
  border-style: solid;
  border-color: rgb(170, 170, 170) rgb(170, 170, 170) rgb(226, 226, 226);
  border-image: initial;
}

.broker-review-re-textarea-button-wrapper {
  text-align: right;
  width: 100%;
}
.broker-review-re-textarea-button {
  margin-left: 20px;
  border: none;
  background-color: rgb(21, 100, 249);
  color: #fff;
  padding: 10px;
  cursor: pointer;
  width: 100px;
  font-size: 12px;
  border-radius: 4px;
}
.broker-review-re-textarea-button:hover {
  opacity: 0.5;
}
.broker-review-textarea-title {
  height: 41px;
  margin-bottom: 20px;
  font-size: 28px;
  text-align: center;
  color: rgb(34, 34, 34);
  font-weight: bold;
}
.broker-review-textarea-title > span {
  color: rgb(21, 100, 249);
}

.broker-review-button-title {
  display: inline-block;
  height: 26px;
  color: rgb(34, 34, 34);
  font-size: 14px;
  line-height: 26px;
  text-align: left;
}

.broker-review-icon-wrapper {
  position: absolute;
  top: 50%;
  left: 160px;
  transform: translateY(-50%);
  font-size: 13px;
  text-align: left;
  color: rgb(136, 136, 136);
  cursor: pointer;
}

.broker-review-icon-wrapper > svg {
  vertical-align: middle;
  width: 24px;
  height: 24px;
}
.broker-review-icon-wrapper > span {
  height: 26px;
  vertical-align: middle;
  margin-left: 5px;
  line-height: 26px;
}

.broker-review-icon2 {
  left: 260px;
}

.broker-review-icon3 {
  left: 360px;
}

.broker-review-icon4 {
  left: 460px;
}

.broker-icon-count-wrapper {
  width: 436px;
  height: 94px;
  margin: 60px auto 40px;
}

.broker-icon-count {
  display: inline-block;
  width: 94px;
  height: 94px;
  background-color: rgb(67, 131, 255);
  border-radius: 50%;
}

.broker-icon-count2 {
  background-color: #48d0ae;
  margin-left: 20px;
}
.broker-icon-count3 {
  background-color: #ffbb31;
  margin-left: 20px;
}
.broker-icon-count4 {
  background-color: #ee5464;
  margin-left: 20px;
}

.broker-icon-count > svg {
  display: block;
  margin: 15px auto 5px;
}

.broker-icon-sub {
  height: 14px;
  font-size: 12px;
  font-weight: 500;
  text-align: center;
  color: rgb(255, 255, 255);
}
.broker-icon-count-number {
  height: 24px;
  font-size: 20px;
  font-weight: 700;
  text-align: center;
  color: rgb(255, 255, 255);
}
.review-write-button {
  float: right;
  width: 138px;
  height: 50px;
  color: rgb(34, 34, 34);
  font-size: 15px;
  font-weight: 500;
  text-align: center;
  border-top-style: initial;
  border-right-style: initial;
  border-bottom-style: initial;
  border-top-color: initial;
  border-right-color: initial;
  border-bottom-color: initial;
  border-bottom-right-radius: 4px;
  background-color: rgb(255, 255, 255);
  border-width: 0px 0px 0px 1px;
  border-image: initial;
  border-left: 1px solid rgb(235, 235, 235);
  cursor: pointer;
}

.review-write-button:hover {
  opacity: 0.5;
}
.broker-review-list-container {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.broker-review-list-wrapper {
  width: 700px;
  display: flex;
  min-height: 155px;
  align-items: center;

  border-top: 1px solid #dddddd;
}

.broker-review-list-icon-wrapper {
  width: 132px;
  text-align: center;
}
.broker-review-list-icon-text {
  font-size: 14px;
  margin-top: 5px;
}

.broker-review-list-information-wrapper {
  display: flex;
  flex-direction: column;
  justify-content: center;
  padding: 0 10px;
  width: 100%;
}

.broker-review-list-profile-wrapper {
  display: flex;
  align-items: center;
}

.broker-reivew-profile-image-wrapper {
  width: 30px;
  height: 30px;
}
.broker-reivew-profile-image-wrapper > img {
  width: 100%;
  height: 100%;
  border-radius: 50%;
}

.broker-review-profile-name {
  margin-left: 20px;
  font-size: 15px;
}

.broker-review-profile-register {
  margin-left: 10px;
  font-size: 13px;
  color: #666666;
}

.broker-review-content {
  margin-top: 20px;
  color: rgb(102, 102, 102);
}

.broker-review-re-write-wrapper {
  padding-left: 150px;
  background-color: rgb(249, 249, 249);
  position: relative;
}
.re-write-arrow {
  width: 10px;
  height: 10px;
  position: absolute;
  top: 50px;
  left: 122px;
  border-left: 1px solid rgb(151, 151, 151);
  border-bottom: 1px solid rgb(151, 151, 151);
}
.reply-modal-header-wrapper {
  display: flex;
}

.reply-modal-header-title {
  margin-left: auto;
}
.reply-modal-close-button-wrapper {
  width: 30px;
  height: 30px;
  margin-left: auto;
  cursor: pointer;
}
.reply-modal-close-button-wrapper > img {
  width: 100%;
  height: 100%;
}
.reply-modal-body-wrapper {
  margin-top: 20px;
  padding-left: 20px;
}
.reply-modal-textarea {
  box-sizing: border-box;
  -webkit-appearance: none;
  resize: none;
  width: 400px;
  height: 70px;
  color: rgb(136, 136, 136);
  font-size: 14px;
  line-height: 22px;
  background-color: rgb(255, 255, 255);
  position: relative;
  border-radius: 4px 4px 0px 0px;
  outline: none;
  padding: 20px;
  border-width: 1px;
  border-style: solid;
  border-color: rgb(170, 170, 170) rgb(170, 170, 170) rgb(226, 226, 226);
  border-image: initial;
}

.reply-modal-button-wrapper {
  margin-top: 20px;
  text-align: right;
}

.reply-modal-button {
  background-color: #3185f8;
  color: #fff;
  width: 120px;
  text-align: center;
  margin-left: auto;
  padding: 10px;
  font-size: 15px;
  cursor: pointer;
}

.re-textarea-update {
  width: 100px;
  background-color: darkblue;
}

.re-textarea-delete {
  width: 100px;
  background-color: #ee5464;
}
</style>