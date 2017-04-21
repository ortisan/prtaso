-- Removing not null constraints
ALTER TABLE "user" ALTER COLUMN username DROP NOT NULL;
ALTER TABLE "user" ALTER COLUMN password DROP NOT NULL;
-- Adding twitter columns
ALTER TABLE "user" ADD COLUMN twitter_user_id varchar(100);
ALTER TABLE "user" ADD COLUMN twitter_token varchar(100);
ALTER TABLE "user" ADD COLUMN twitter_token_secret varchar(100);
ALTER TABLE "user" ADD COLUMN twitter_verifier varchar(100);
